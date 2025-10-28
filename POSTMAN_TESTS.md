# 🧪 Guía Completa de Pruebas con Postman - Comuctiva Backend

## 📋 Índice
1. [Configuración Inicial](#configuración-inicial)
2. [Autenticación (JWT)](#autenticación-jwt)
3. [Pruebas por Rol](#pruebas-por-rol)
4. [Endpoints Disponibles](#endpoints-disponibles)
5. [Ejemplos de Respuestas](#ejemplos-de-respuestas)

---

## ⚙️ Configuración Inicial

### URL Base
```
http://localhost:8080
```

### Usuarios de Prueba Precargados

| Rol | Documento | Contraseña | Permisos |
|-----|-----------|------------|----------|
| **Administrador** | `22222222` | `admin123` | ✅ GET, POST, PUT, DELETE |
| **Cliente 1** | `55555555` | `1234` | ✅ GET, POST, PUT ❌ DELETE |
| **Cliente 2** | `66666666` | `1234` | ✅ GET, POST, PUT ❌ DELETE |

---

## 🔐 Autenticación (JWT)

### 1. Login - Obtener Token JWT

**Endpoint:**
```
POST http://localhost:8080/api/usuario/login
```

**Headers:**
```
Content-Type: application/json
```

**Body (Admin):**
```json
{
  "tipDocId": 1,
  "numDoc": "22222222",
  "password": "admin123"
}
```

**Body (Cliente):**
```json
{
  "tipDocId": 1,
  "numDoc": "55555555",
  "password": "1234"
}
```

**Respuesta Exitosa (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "22222222",
  "roles": ["Administrador"]
}
```

**Respuesta de Error (401 Unauthorized):**
```json
{
  "error": "Credenciales inválidas"
}
```

---

## 🔑 Usar el Token en las Peticiones

### Configurar el Header de Autorización

Para todas las peticiones protegidas, agrega este header:

```
Authorization: Bearer {tu-token-aqui}
```

**Ejemplo:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyMjIyMjIyMiIsInJvbGVzIjpbIkFkbWluaXN0cmFkb3IiXSwiaWF0IjoxNjk4NTA1MjAwfQ.abc123...
```

---

## 👥 Pruebas por Rol

### ✅ Pruebas como ADMINISTRADOR

#### 1. Login como Admin
```http
POST http://localhost:8080/api/usuario/login
Content-Type: application/json

{
  "tipDocId": 1,
  "numDoc": "22222222",
  "password": "admin123"
}
```

#### 2. Listar Productos (GET)
```http
GET http://localhost:8080/api/producto
Authorization: Bearer {token-admin}
```
**Resultado esperado:** ✅ 200 OK - Lista de productos

#### 3. Crear Producto (POST)
```http
POST http://localhost:8080/api/producto
Authorization: Bearer {token-admin}
Content-Type: application/json

{
  "nom_produc": "Manzana Roja",
  "precio": 2500.00,
  "desc_produc": "Manzana fresca importada",
  "cant_disponible": 100,
  "estado": true,
  "tipoProducto": {
    "id_tipo_producto": 1
  },
  "unidadMedida": {
    "id_unidad_medida": 1
  }
}
```
**Resultado esperado:** ✅ 201 CREATED - Producto creado

#### 4. Actualizar Producto (PUT)
```http
PUT http://localhost:8080/api/producto/1
Authorization: Bearer {token-admin}
Content-Type: application/json

{
  "nom_produc": "Manzana Roja Premium",
  "precio": 3000.00,
  "desc_produc": "Manzana fresca importada de alta calidad",
  "cant_disponible": 80,
  "estado": true,
  "tipoProducto": {
    "id_tipo_producto": 1
  },
  "unidadMedida": {
    "id_unidad_medida": 1
  }
}
```
**Resultado esperado:** ✅ 200 OK - Producto actualizado

#### 5. Eliminar Producto (DELETE) ⭐ SOLO ADMIN
```http
DELETE http://localhost:8080/api/producto/1
Authorization: Bearer {token-admin}
```
**Resultado esperado:** ✅ 200 OK - Producto eliminado

---

### 👤 Pruebas como CLIENTE

#### 1. Login como Cliente
```http
POST http://localhost:8080/api/usuario/login
Content-Type: application/json

{
  "tipDocId": 1,
  "numDoc": "55555555",
  "password": "1234"
}
```

#### 2. Listar Productos (GET)
```http
GET http://localhost:8080/api/producto
Authorization: Bearer {token-cliente}
```
**Resultado esperado:** ✅ 200 OK - Lista de productos

#### 3. Crear Producto (POST)
```http
POST http://localhost:8080/api/producto
Authorization: Bearer {token-cliente}
Content-Type: application/json

{
  "nom_produc": "Pera Verde",
  "precio": 1800.00,
  "desc_produc": "Pera nacional",
  "cant_disponible": 50,
  "estado": true,
  "tipoProducto": {
    "id_tipo_producto": 1
  },
  "unidadMedida": {
    "id_unidad_medida": 1
  }
}
```
**Resultado esperado:** ✅ 201 CREATED - Producto creado

#### 4. Actualizar Producto (PUT)
```http
PUT http://localhost:8080/api/producto/2
Authorization: Bearer {token-cliente}
Content-Type: application/json

{
  "nom_produc": "Pera Verde Orgánica",
  "precio": 2200.00,
  "desc_produc": "Pera nacional orgánica",
  "cant_disponible": 40,
  "estado": true,
  "tipoProducto": {
    "id_tipo_producto": 1
  },
  "unidadMedida": {
    "id_unidad_medida": 1
  }
}
```
**Resultado esperado:** ✅ 200 OK - Producto actualizado

#### 5. Eliminar Producto (DELETE) ⚠️ PROHIBIDO
```http
DELETE http://localhost:8080/api/producto/2
Authorization: Bearer {token-cliente}
```
**Resultado esperado:** ❌ **403 FORBIDDEN**
```json
{
  "error": "Acceso Denegado",
  "message": "No tienes permisos para realizar esta acción",
  "timestamp": "2025-10-28T16:55:00",
  "path": "/api/producto/2"
}
```

---

## 📚 Endpoints Disponibles

### 🔐 Autenticación
| Método | Endpoint | Descripción | Auth Requerida |
|--------|----------|-------------|----------------|
| POST | `/api/usuario/login` | Iniciar sesión y obtener JWT | ❌ No |
| POST | `/api/usuario` | Registrar nuevo usuario | ❌ No |

### � Usuarios
| Método | Endpoint | Descripción | Admin | Cliente |
|--------|----------|-------------|-------|---------|
| GET | `/api/usuario` | Listar usuarios | ✅ | ✅ |
| GET | `/api/usuario/{id}` | Obtener usuario por ID | ✅ | ✅ |
| POST | `/api/usuario` | Crear usuario | ❌ Público | ❌ Público |
| PUT | `/api/usuario/{id}` | Actualizar usuario | ✅ | ✅ |
| DELETE | `/api/usuario/{id}` | Eliminar usuario | ✅ | ❌ |

### � Productos
| Método | Endpoint | Descripción | Admin | Cliente |
|--------|----------|-------------|-------|---------|
| GET | `/api/producto` | Listar todos los productos | ✅ Público | ✅ Público |
| GET | `/api/producto/{id}` | Obtener producto por ID | ✅ Público | ✅ Público |
| POST | `/api/producto` | Crear nuevo producto | ✅ | ✅ |
| PUT | `/api/producto/{id}` | Actualizar producto | ✅ | ✅ |
| DELETE | `/api/producto/{id}` | Eliminar producto | ✅ | ❌ |

### 🛒 Compras
| Método | Endpoint | Descripción | Admin | Cliente |
|--------|----------|-------------|-------|---------|
| GET | `/api/compras` | Listar compras | ✅ | ✅ |
| GET | `/api/compras/{id}` | Obtener compra por ID | ✅ | ✅ |
| POST | `/api/compras` | Crear compra | ✅ | ✅ |
| PUT | `/api/compras/{id}` | Actualizar compra | ✅ | ✅ |
| DELETE | `/api/compras/{id}` | Eliminar compra | ✅ | ❌ |

### 📦 Pedidos
| Método | Endpoint | Descripción | Admin | Cliente |
|--------|----------|-------------|-------|---------|
| GET | `/api/pedidos` | Listar pedidos | ✅ | ✅ |
| GET | `/api/pedidos/{id}` | Obtener pedido por ID | ✅ | ✅ |
| POST | `/api/pedidos` | Crear pedido | ✅ | ✅ |
| PUT | `/api/pedidos/{id}` | Actualizar pedido | ✅ | ✅ |
| DELETE | `/api/pedidos/{id}` | Eliminar pedido | ✅ | ❌ |

### 💬 Comentarios
| Método | Endpoint | Descripción | Admin | Cliente |
|--------|----------|-------------|-------|---------|
| GET | `/api/comentarios` | Listar comentarios | ✅ Público | ✅ Público |
| GET | `/api/comentarios/producto/{id}` | Comentarios por producto | ✅ Público | ✅ Público |
| POST | `/api/comentarios` | Crear comentario | ✅ | ✅ |
| PUT | `/api/comentarios/{id}` | Actualizar comentario | ✅ | ✅ |
| DELETE | `/api/comentarios/{id}` | Eliminar comentario | ✅ | ❌ |

### � Carrito
| Método | Endpoint | Descripción | Admin | Cliente |
|--------|----------|-------------|-------|---------|
| GET | `/api/carrito` | Listar carritos | ✅ | ✅ |
| GET | `/api/carrito/{id}` | Obtener carrito por ID | ✅ | ✅ |
| POST | `/api/carrito` | Crear carrito | ✅ | ✅ |
| PUT | `/api/carrito/{id}` | Actualizar carrito | ✅ | ✅ |
| DELETE | `/api/carrito/{id}` | Eliminar carrito | ✅ | ❌ |

### 🚚 Productos del Carrito
| Método | Endpoint | Descripción | Admin | Cliente |
|--------|----------|-------------|-------|---------|
| POST | `/api/produc_carri` | Agregar producto al carrito | ✅ | ✅ |

### 📦 Productos del Pedido
| Método | Endpoint | Descripción | Admin | Cliente |
|--------|----------|-------------|-------|---------|
| POST | `/api/pedi_produc` | Agregar producto al pedido | ✅ | ✅ |

### 📍 Direcciones y Ubicaciones
| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| GET | `/api/departamento` | Listar departamentos | ✅ Público |
| GET | `/api/departamento/listdepart` | Lista DTO departamentos | ✅ Público |
| GET | `/api/muni` | Listar municipios | ✅ Público |
| GET | `/api/barrio` | Listar barrios | ✅ |
| GET | `/api/barrio/{id}` | Obtener barrio por ID | ✅ |
| POST | `/api/barrio` | Crear barrio | ✅ |
| PUT | `/api/barrio/{id}` | Actualizar barrio | ✅ |
| DELETE | `/api/barrio/{id}` | Eliminar barrio | ✅ Admin |
| GET | `/api/vias` | Listar vías | ✅ Público |
| GET | `/api/vias/listvias` | Lista DTO vías | ✅ Público |
| GET | `/api/barrvere` | Listar barrios y veredas | ✅ Público |

### 🏠 Direcciones de Usuario
| Método | Endpoint | Descripción | Admin | Cliente |
|--------|----------|-------------|-------|---------|
| GET | `/api/direcciones` | Listar direcciones | ✅ | ✅ |
| GET | `/api/direcciones/{id}` | Obtener dirección por ID | ✅ | ✅ |
| POST | `/api/direcciones` | Crear dirección | ✅ | ✅ |
| PUT | `/api/direcciones/{id}` | Actualizar dirección | ✅ | ✅ |
| DELETE | `/api/direcciones/{id}` | Eliminar dirección | ✅ | ❌ |

### 📋 Catálogos y Configuración
| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| GET | `/api/tipdoc` | Listar tipos de documento | ❌ Público |
| GET | `/api/tipdoc/tipdocList` | Lista DTO tipos de documento | ❌ Público |
| GET | `/api/Unidad_Medida` | Listar unidades de medida | ❌ Público |
| GET | `/api/estado` | Listar estados | ✅ Público |
| GET | `/api/estado/listest` | Lista DTO estados | ✅ Público |
| GET | `/api/Tpago` | Listar tipos de pago | ✅ Público |
| GET | `/api/descuentos` | Listar descuentos | ✅ Público |
| GET | `/api/transportadora` | Listar transportadoras | ✅ Público |
| GET | `/api/obser` | Listar observaciones | ✅ Público |

---

## 📝 Ejemplos de Cuerpos de Petición (Request Body)

### Crear Usuario
```json
{
  "nom_Usu": "Carlos",
  "apell1": "González",
  "apell2": "Pérez",
  "tel": 3001234567,
  "tel2": 3009876543,
  "correo": "carlos@ejemplo.com",
  "numDoc": 12345678,
  "password": "password123",
  "tip_Doc": {
    "id_tipdocu": 1
  }
}
```

### Crear Compra
```json
{
  "fecha": "2025-10-28",
  "total": 50000.00,
  "estado": "PENDIENTE",
  "usuario": {
    "id_Usuario": 1
  },
  "direccion": {
    "id_direccion": 1
  },
  "metodoPago": {
    "id_metodo_pago": 1
  }
}
```

### Crear Pedido
```json
{
  "cant_pedido": 10,
  "fecha_pedido": "2025-10-28",
  "compra": {
    "id_compra": 1
  }
}
```

### Crear Comentario
```json
{
  "calif": 5,
  "comentario": "Excelente producto, muy fresco",
  "fecha": "2025-10-28",
  "compProduc": {
    "id": {
      "compraId": 1,
      "productoId": 1
    }
  },
  "usuario": {
    "id_Usuario": 1
  }
}
```

---

## ✅ Checklist de Pruebas

### Autenticación
- [ ] Login exitoso con Admin
- [ ] Login exitoso con Cliente
- [ ] Login fallido con credenciales incorrectas
- [ ] Login fallido con usuario inexistente

### Permisos de Admin
- [ ] Admin puede listar productos (GET)
- [ ] Admin puede crear productos (POST)
- [ ] Admin puede actualizar productos (PUT)
- [ ] Admin puede eliminar productos (DELETE)
- [ ] Admin puede eliminar usuarios (DELETE)
- [ ] Admin puede eliminar compras (DELETE)

### Permisos de Cliente
- [ ] Cliente puede listar productos (GET)
- [ ] Cliente puede crear productos (POST)
- [ ] Cliente puede actualizar productos (PUT)
- [ ] Cliente **NO** puede eliminar productos (DELETE) → 403 Forbidden
- [ ] Cliente **NO** puede eliminar usuarios (DELETE) → 403 Forbidden
- [ ] Cliente **NO** puede eliminar compras (DELETE) → 403 Forbidden

### Validaciones
- [ ] No se puede acceder sin token → 401 Unauthorized
- [ ] Token expirado → 401 Unauthorized
- [ ] Token inválido → 401 Unauthorized
- [ ] Campos requeridos faltantes → 400 Bad Request

---

## 🐛 Códigos de Respuesta HTTP

| Código | Descripción | Cuándo se usa |
|--------|-------------|---------------|
| **200 OK** | Éxito | GET, PUT, DELETE exitosos |
| **201 Created** | Recurso creado | POST exitoso |
| **400 Bad Request** | Datos inválidos | Validación fallida |
| **401 Unauthorized** | No autenticado | Sin token o token inválido |
| **403 Forbidden** | Sin permisos | Cliente intenta DELETE |
| **404 Not Found** | Recurso no existe | ID no encontrado |
| **500 Internal Server Error** | Error del servidor | Error no controlado |

---

## 💡 Consejos para Postman

### 1. Crear Variables de Entorno

**Variables sugeridas:**
- `base_url` = `http://localhost:8080`
- `token_admin` = `{token obtenido del login admin}`
- `token_cliente` = `{token obtenido del login cliente}`

**Uso:**
```
GET {{base_url}}/api/producto
Authorization: Bearer {{token_admin}}
```

### 2. Crear una Colección

Organiza tus peticiones en carpetas:
```
📁 Comuctiva API
  📁 Auth
    ➡️ Login Admin
    ➡️ Login Cliente
  📁 Productos (Admin)
    ➡️ Listar
    ➡️ Crear
    ➡️ Actualizar
    ➡️ Eliminar
  📁 Productos (Cliente)
    ➡️ Listar
    ➡️ Crear
    ➡️ Actualizar
    ➡️ Eliminar (Debe fallar)
```

### 3. Tests Automatizados en Postman

Agrega en la pestaña **Tests** de cada petición:

**Para Login:**
```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Response has token", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property('token');
});

// Guardar token automáticamente
pm.environment.set("token_admin", pm.response.json().token);
```

**Para DELETE como Cliente (debe fallar):**
```javascript
pm.test("Status code is 403 Forbidden", function () {
    pm.response.to.have.status(403);
});

pm.test("Access denied message", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.error).to.eql("Acceso Denegado");
});
```

---

## 🎯 Escenarios de Prueba Completos

### Escenario 1: Flujo Completo de Admin
1. Login como Admin → Guardar token
2. Crear producto
3. Actualizar el producto creado
4. Listar todos los productos
5. Eliminar el producto
6. Verificar que ya no existe

### Escenario 2: Restricción de Cliente
1. Login como Cliente → Guardar token
2. Crear producto
3. Intentar eliminar producto → Debe recibir 403 Forbidden
4. Verificar mensaje de error personalizado

### Escenario 3: Seguridad
1. Intentar GET sin token → 401 Unauthorized
2. Intentar con token inválido → 401 Unauthorized
3. Login con credenciales incorrectas → 401 Unauthorized

---

## 🎯 Pruebas Adicionales Recomendadas

### 📦 Prueba de Productos

#### Listar todos los productos (Público)
```http
GET http://localhost:8080/api/producto
```
**Sin autenticación** - Debe funcionar ✅

#### Obtener producto por ID
```http
GET http://localhost:8080/api/producto/1
```
**Sin autenticación** - Debe funcionar ✅

### 🛒 Prueba de Carrito

#### Crear carrito
```http
POST http://localhost:8080/api/carrito
Authorization: Bearer {token}
Content-Type: application/json

{
  "usuario": {
    "id_Usuario": 1
  }
}
```

#### Agregar producto al carrito
```http
POST http://localhost:8080/api/produc_carri
Authorization: Bearer {token}
Content-Type: application/json

{
  "producCarriId": {
    "carritoId": 1,
    "productoId": 1
  },
  "cantidad": 3
}
```

### 🏠 Prueba de Direcciones

#### Listar departamentos (Público)
```http
GET http://localhost:8080/api/departamento
```

#### Listar municipios
```http
GET http://localhost:8080/api/muni
```

#### Crear dirección de usuario
```http
POST http://localhost:8080/api/direcciones
Authorization: Bearer {token}
Content-Type: application/json

{
  "numDir": "123",
  "tipDirec": {
    "id_vias": 1
  },
  "letra": "A",
  "bis": false,
  "letraDos": "B",
  "cudra": "45",
  "barr_vere": {
    "id_barr_vere": 1
  },
  "usuario": {
    "id_Usuario": 1
  }
}
```

### 🛒 Flujo Completo de Compra

#### 1. Login
```http
POST http://localhost:8080/api/usuario/login
Content-Type: application/json

{
  "tipDocId": 1,
  "numDoc": "55555555",
  "password": "1234"
}
```

#### 2. Ver productos disponibles
```http
GET http://localhost:8080/api/producto
```

#### 3. Crear compra
```http
POST http://localhost:8080/api/compras
Authorization: Bearer {token}
Content-Type: application/json

{
  "fecha": "2025-10-28",
  "total": 50000.00,
  "estado": "PENDIENTE",
  "usuario": {
    "id_Usuario": 2
  },
  "direccion": {
    "id_direccion": 1
  },
  "metodoPago": {
    "id_metodo_pago": 1
  }
}
```

#### 4. Crear pedido asociado a la compra
```http
POST http://localhost:8080/api/pedidos
Authorization: Bearer {token}
Content-Type: application/json

{
  "cant_pedido": 10,
  "fecha_pedido": "2025-10-28",
  "compra": {
    "id_compra": 1
  }
}
```

#### 5. Agregar productos al pedido
```http
POST http://localhost:8080/api/pedi_produc
Authorization: Bearer {token}
Content-Type: application/json

{
  "pediProducId": {
    "pedidoId": 1,
    "productoId": 1
  },
  "cantidad": 5,
  "precio_Unitario": 2500.00
}
```

#### 6. Crear comentario del producto
```http
POST http://localhost:8080/api/comentarios
Authorization: Bearer {token}
Content-Type: application/json

{
  "calif": 5,
  "comentario": "Excelente producto, muy fresco y de buena calidad",
  "fecha": "2025-10-28",
  "compProduc": {
    "id": {
      "compraId": 1,
      "productoId": 1
    }
  },
  "usuario": {
    "id_Usuario": 2
  }
}
```

### 📋 Pruebas de Catálogos (Público)

#### Tipos de documento
```http
GET http://localhost:8080/api/tipdoc
```

#### Unidades de medida
```http
GET http://localhost:8080/api/Unidad_Medida
```

#### Estados
```http
GET http://localhost:8080/api/estado
```

#### Tipos de pago
```http
GET http://localhost:8080/api/Tpago
```

#### Transportadoras
```http
GET http://localhost:8080/api/transportadora
```

---

## 📞 Soporte

Si encuentras algún error o tienes dudas:
1. Verifica que el backend esté corriendo en `http://localhost:8080`
2. Verifica que XAMPP esté activo con MySQL
3. Revisa los logs del servidor para más detalles
4. Asegúrate de usar el token correcto y actualizado

---

**¡Happy Testing! 🚀**
