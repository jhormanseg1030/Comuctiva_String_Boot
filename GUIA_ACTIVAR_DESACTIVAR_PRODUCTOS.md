# 🆕 Endpoints para Activar/Desactivar Productos

## Descripción
Se han agregado nuevos endpoints para poder activar y desactivar productos de manera sencilla usando el campo booleano `activo`.

## Endpoints Disponibles

### 1. Activar Producto 
```http
PUT /api/producto/{id_producto}/activar
```
**Descripción**: Activa un producto específico (activo = true)
**Respuesta exitosa**:
```json
{
    "Mensaje": "Producto activado exitosamente",
    "activo": true
}
```

### 2. Desactivar Producto
```http
PUT /api/producto/{id_producto}/desactivar
```
**Descripción**: Desactiva un producto específico (activo = false)
**Respuesta exitosa**:
```json
{
    "Mensaje": "Producto desactivado exitosamente", 
    "activo": false
}
```

### 3. Toggle Estado (Cambiar Estado) - ⭐ RECOMENDADO
```http
PUT /api/producto/{id_producto}/toggle-estado
```
**Descripción**: Cambia automáticamente el estado del producto (si está activo lo desactiva, si está inactivo lo activa)
**Respuesta exitosa**:
```json
{
    "Mensaje": "Producto desactivado exitosamente", 
    "activo": false,
    "producto": "Nombre del Producto"
}
```

## Ejemplos de Uso

### Con cURL:
```bash
# Activar producto
curl -X PUT http://localhost:8080/api/producto/1/activar

# Desactivar producto  
curl -X PUT http://localhost:8080/api/producto/1/desactivar

# Cambiar estado automáticamente
curl -X PUT http://localhost:8080/api/producto/1/toggle-estado
```

### Con JavaScript (Frontend):
```javascript
// Función para cambiar estado de producto
async function toggleProductoEstado(idProducto) {
    try {
        const response = await fetch(`/api/producto/${idProducto}/toggle-estado`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` // Si usas JWT
            }
        });
        
        const result = await response.json();
        
        if (response.ok) {
            console.log(result.Mensaje);
            // Actualizar UI según result.activo
            updateProductoUI(idProducto, result.activo);
        } else {
            console.error('Error:', result.Error);
        }
    } catch (error) {
        console.error('Error de red:', error);
    }
}

// Función específica para activar
async function activarProducto(idProducto) {
    const response = await fetch(`/api/producto/${idProducto}/activar`, {
        method: 'PUT'
    });
    return await response.json();
}

// Función específica para desactivar
async function desactivarProducto(idProducto) {
    const response = await fetch(`/api/producto/${idProducto}/desactivar`, {
        method: 'PUT'
    });
    return await response.json();
}
```

## Cambios Realizados

### 1. Modelo Producto
- ✅ Campo `activo` ya existía con valor por defecto `true`

### 2. ProductoDto
- ✅ Agregado campo `private Boolean activo;`

### 3. ProductoMapperImple  
- ✅ Agregado mapeo del campo activo: `dto.setActivo(producto.getActivo());`

### 4. ProductoController
- ✅ Agregado endpoint `/activar`
- ✅ Agregado endpoint `/desactivar` 
- ✅ Agregado endpoint `/toggle-estado` (recomendado)

### 5. Base de Datos
- ✅ Script SQL para agregar la columna si no existe: `agregar_campo_activo_producto.sql`

## Notas Importantes

1. **Toggle Estado**: Es el endpoint más recomendado porque automáticamente detecta el estado actual y lo cambia.

2. **Validación**: Todos los endpoints incluyen manejo de errores con respuestas JSON apropiadas.

3. **Compatibilidad**: Los productos existentes se mantienen activos por defecto.

4. **Servicios**: Utiliza los métodos existentes `desactivarProducto()` y `restaurarProducto()` del servicio.

## Testing con Postman

Puedes probar estos endpoints en Postman:

1. Crear una nueva request PUT
2. URL: `http://localhost:8080/api/producto/1/toggle-estado` 
3. Headers: `Content-Type: application/json`
4. Enviar la request
5. Verificar la respuesta JSON

¡Ya puedes usar estos endpoints para activar y desactivar productos de forma sencilla! 🎉
