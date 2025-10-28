# Actualización de Encriptación de Contraseñas

## Resumen de Cambios

Se ha implementado el encriptado de contraseñas usando **BCrypt** para mejorar la seguridad del sistema. Todos los usuarios (Administradores y Clientes) ahora tendrán sus contraseñas encriptadas.

## Archivos Modificados

### 1. `SecurityConfig.java`
- Se agregó el bean `PasswordEncoder` que utiliza `BCryptPasswordEncoder`
- Este bean se inyecta en todos los servicios que necesitan encriptar o verificar contraseñas

### 2. `UsuarioMapperImple.java`
- Se inyectó `PasswordEncoder` en el constructor
- El método `toUsuario()` ahora encripta automáticamente la contraseña antes de crear el usuario
- Esto aplica a todos los usuarios que se registren desde el formulario

### 3. `DataLoader.java`
- Se inyectó `PasswordEncoder` en el método `loadData()`
- Las contraseñas del usuario Admin y usuarios de prueba se encriptan al inicializar la base de datos
- Usuario Admin: `admin123` (texto plano) → Hash BCrypt
- Usuario de prueba: `1234` (texto plano) → Hash BCrypt

### 4. `UsuarioRepositories.java`
- Se cambió el método `findByLogin()` por `findByTipDocAndNumDoc()`
- Ya no se compara la contraseña en la consulta SQL, se hace en el servicio con BCrypt

### 5. `UsuarioServicesImple.java`
- Se inyectó `PasswordEncoder` en el constructor
- Método `buscarPorLogin()`: Ahora busca el usuario por documento y luego verifica la contraseña con `passwordEncoder.matches()`
- Método `actualizarUsuario()`: Encripta la nueva contraseña si se está actualizando

## Cómo Funciona BCrypt

BCrypt es un algoritmo de hash de una sola vía que:
- Genera un hash único cada vez (incluye un "salt" aleatorio)
- Es computacionalmente costoso, lo que dificulta ataques de fuerza bruta
- No se puede "desencriptar" - solo se puede verificar si una contraseña coincide

### Ejemplo de Hash BCrypt
```
Contraseña original: admin123
Hash BCrypt: $2a$10$N9qo8uLOickgx2ZMRZoMye.IVI1lrPn9R6y4KW4ZP1ybYHYwXpGQa
```

## Migración de Datos Existentes

### IMPORTANTE: Reiniciar la Base de Datos

Como se usa `DataLoader` para crear usuarios iniciales, y ahora las contraseñas se encriptan automáticamente:

**Opción 1: Reiniciar la Base de Datos (Recomendado para desarrollo)**
```sql
DROP DATABASE IF EXISTS Comuctiva;
```
Luego ejecutar nuevamente:
- `DDL.sql`
- `DML.sql`
- Reiniciar la aplicación Spring Boot

Los usuarios creados por `DataLoader` tendrán sus contraseñas encriptadas automáticamente.

**Opción 2: Actualizar Contraseñas Manualmente (Producción)**

Si ya tienes usuarios en producción, **NO PUEDES** actualizar las contraseñas directamente en SQL porque necesitas el hash BCrypt. Opciones:

1. **Crear un endpoint temporal** para que los usuarios restablezcan sus contraseñas
2. **Usar un script de Java** que lea las contraseñas actuales y las actualice con BCrypt
3. **Ejecutar un CommandLineRunner temporal** que migre las contraseñas existentes

## Usuarios de Prueba

Después de reiniciar la base de datos, estos son los usuarios de prueba:

### Usuario Administrador
- **Tipo de Documento**: Cédula de Ciudadanía (ID: 1)
- **Número de Documento**: 22222222
- **Contraseña**: admin123
- **Rol**: Admin

### Usuario de Prueba
- **Tipo de Documento**: Cédula de Ciudadanía (ID: 1)
- **Número de Documento**: 11111111
- **Contraseña**: 1234
- **Rol**: Vendedor (por defecto si no tiene rol asignado)

## Flujo de Registro de Nuevos Usuarios

1. El cliente completa el formulario de registro en el frontend
2. El frontend envía los datos al endpoint `POST /api/usuario`
3. El `UsuarioController` llama a `usuarioServices.crearUsuario()`
4. El `UsuarioMapper` convierte el DTO a entidad Usuario
5. **Durante la conversión, la contraseña se encripta automáticamente**
6. El usuario se guarda en la base de datos con la contraseña encriptada

## Flujo de Login

1. El cliente ingresa tipo de documento, número y contraseña
2. El frontend envía los datos al endpoint `POST /api/usuario/login`
3. El `UsuarioController` llama a `usuarioServices.buscarPorLogin()`
4. El servicio busca el usuario por tipo y número de documento
5. **El servicio verifica la contraseña usando `passwordEncoder.matches()`**
6. Si coincide, se genera y retorna el token JWT
7. Si no coincide, retorna error 401 Unauthorized

## Verificación de Implementación

Para verificar que todo funciona correctamente:

### 1. Verificar que la aplicación inicia sin errores
```bash
cd Comuctiva_String_Boot
./mvnw spring-boot:run
```

### 2. Probar el registro de un nuevo cliente
```bash
POST http://localhost:8080/api/usuario
Content-Type: application/json

{
  "nombre": "Pedro",
  "apellido": "García",
  "apellido2": "López",
  "telefono": 3001234567,
  "telefono2": 3009876543,
  "correo": "pedro@example.com",
  "numdocumento": 33333333,
  "password": "miPassword123",
  "tipId": 1
}
```

### 3. Verificar en la base de datos
```sql
SELECT ID_Usuario, NomUsu, NumDoc, Password FROM Usuario WHERE NumDoc = 33333333;
```
Deberías ver que el campo `Password` contiene un hash BCrypt que empieza con `$2a$` o `$2b$`.

### 4. Probar el login
```bash
POST http://localhost:8080/api/usuario/login
Content-Type: application/json

{
  "tipDocId": 1,
  "numDoc": 33333333,
  "password": "miPassword123"
}
```

Debería retornar un token JWT válido.

### 5. Probar login con contraseña incorrecta
```bash
POST http://localhost:8080/api/usuario/login
Content-Type: application/json

{
  "tipDocId": 1,
  "numDoc": 33333333,
  "password": "passwordIncorrecta"
}
```

Debería retornar error 401 Unauthorized.

## Seguridad Adicional Recomendada

1. **Validación de Contraseñas Fuertes**: Implementar validación en el frontend y backend para requerir:
   - Mínimo 8 caracteres
   - Al menos una letra mayúscula
   - Al menos un número
   - Al menos un carácter especial

2. **Limitación de Intentos de Login**: Implementar bloqueo temporal después de varios intentos fallidos

3. **HTTPS**: Asegurarse de usar HTTPS en producción para proteger las contraseñas durante la transmisión

4. **Tokens de Recuperación**: Implementar sistema de recuperación de contraseña por correo electrónico

## Soporte

Si tienes problemas con la implementación:
1. Verifica que todas las dependencias están en `pom.xml` (Spring Security ya está incluido)
2. Asegúrate de reiniciar la aplicación después de los cambios
3. Revisa los logs de la aplicación para identificar errores
4. Verifica que la base de datos esté correctamente inicializada
