# Guía Rápida - Encriptación de Contraseñas

## ✅ ¿Qué se ha implementado?

Se ha implementado **encriptación BCrypt** para todas las contraseñas de usuarios (Administradores y Clientes).

## 🔧 Cambios Realizados

1. **SecurityConfig.java** - Agregado PasswordEncoder (BCrypt)
2. **UsuarioMapperImple.java** - Encripta contraseñas al crear usuarios
3. **DataLoader.java** - Encripta contraseñas de usuarios iniciales
4. **UsuarioRepositories.java** - Cambiado método de búsqueda de login
5. **UsuarioServicesImple.java** - Verifica contraseñas con BCrypt en login y actualización

## 🚀 Pasos para Activar

### Paso 1: Reiniciar la Base de Datos (Recomendado)

```sql
-- Ejecutar en MySQL
DROP DATABASE IF EXISTS Comuctiva;
```

Luego ejecuta nuevamente tus scripts:
- `DDL.sql`
- `DML.sql`

### Paso 2: Reiniciar la Aplicación Spring Boot

```bash
cd Comuctiva_String_Boot
./mvnw clean spring-boot:run
```

O si usas Windows PowerShell:
```powershell
cd Comuctiva_String_Boot
.\mvnw.cmd clean spring-boot:run
```

### Paso 3: Verificar que Funciona

Los usuarios de prueba son:

**Administrador:**
- Tipo Doc: 1 (Cédula)
- Número: 22222222
- Contraseña: admin123

**Usuario de Prueba:**
- Tipo Doc: 1 (Cédula)
- Número: 11111111
- Contraseña: 1234

## 🧪 Pruebas con Postman

### 1. Probar Login del Admin

```http
POST http://localhost:8080/api/usuario/login
Content-Type: application/json

{
  "tipDocId": 1,
  "numDoc": 22222222,
  "password": "admin123"
}
```

**Respuesta esperada:** Token JWT y datos del usuario

### 2. Registrar un Nuevo Cliente

```http
POST http://localhost:8080/api/usuario
Content-Type: application/json

{
  "nombre": "María",
  "apellido": "Rodríguez",
  "apellido2": "Pérez",
  "telefono": 3001234567,
  "telefono2": 3009876543,
  "correo": "maria@example.com",
  "numdocumento": 44444444,
  "password": "miPassword123",
  "tipId": 1
}
```

**Respuesta esperada:** Usuario creado con éxito (la contraseña se encripta automáticamente)

### 3. Login del Nuevo Cliente

```http
POST http://localhost:8080/api/usuario/login
Content-Type: application/json

{
  "tipDocId": 1,
  "numDoc": 44444444,
  "password": "miPassword123"
}
```

**Respuesta esperada:** Token JWT y datos del usuario

### 4. Verificar Hash de Contraseña (Endpoint de Prueba)

```http
POST http://localhost:8080/api/test/encrypt
Content-Type: application/json

{
  "password": "admin123"
}
```

**Respuesta esperada:**
```json
{
  "plainPassword": "admin123",
  "encryptedPassword": "$2a$10$...(hash largo)...",
  "length": "60"
}
```

## 🔍 Verificar en la Base de Datos

```sql
-- Ver todas las contraseñas encriptadas
SELECT ID_Usuario, NomUsu, NumDoc, 
       SUBSTRING(Password, 1, 20) as Password_Hash 
FROM Usuario;
```

Deberías ver que las contraseñas empiezan con `$2a$10$` o `$2b$10$`

## ⚠️ Migración de Datos Existentes (Opcional)

Si ya tienes usuarios en la base de datos con contraseñas sin encriptar:

### Opción A: Usar el Script de Migración

1. Abre el archivo `PasswordMigrationRunner.java`
2. Descomenta la línea `@Component` (línea 18)
3. Reinicia la aplicación
4. El script migrará automáticamente todas las contraseñas
5. **IMPORTANTE:** Después de la migración, vuelve a comentar `@Component`

### Opción B: Reiniciar la Base de Datos

Es más simple y recomendado para desarrollo:
1. Elimina la base de datos
2. Ejecuta DDL.sql y DML.sql
3. Reinicia la aplicación

## 📝 Notas Importantes

1. **Las contraseñas encriptadas NO se pueden desencriptar** - Solo se pueden verificar
2. **Cada vez que encriptas la misma contraseña obtienes un hash diferente** - Esto es normal con BCrypt
3. **El método `passwordEncoder.matches()` es el único para verificar contraseñas**
4. **Nunca almacenes contraseñas en texto plano**

## 🔐 Seguridad en Producción

Antes de pasar a producción:

1. ✅ Eliminar el archivo `PasswordTestController.java`
2. ✅ Eliminar el archivo `PasswordMigrationRunner.java` (o asegurarse de que `@Component` esté comentado)
3. ✅ Cambiar las contraseñas por defecto del Admin
4. ✅ Usar HTTPS en todos los endpoints
5. ✅ Implementar validación de contraseñas fuertes

## ❓ Solución de Problemas

### Error: "Credenciales inválidas"
- Verifica que estés usando las contraseñas correctas
- Asegúrate de haber reiniciado la aplicación después de los cambios
- Verifica que la base de datos esté correctamente inicializada

### Error: "No se puede iniciar la aplicación"
- Verifica que MySQL esté corriendo
- Verifica las credenciales en `application.properties`
- Revisa los logs de la aplicación

### Las contraseñas no se encriptan
- Verifica que `PasswordEncoder` esté inyectado en `UsuarioMapperImple`
- Revisa los logs para ver si hay errores
- Asegúrate de que Spring Security esté en el `pom.xml`

## 📞 Soporte

Si tienes dudas o problemas:
1. Revisa el archivo `ACTUALIZACION_ENCRIPTACION_PASSWORD.md` para más detalles
2. Verifica los logs de la aplicación
3. Usa el endpoint `/api/test/info` para verificar que BCrypt está activo
