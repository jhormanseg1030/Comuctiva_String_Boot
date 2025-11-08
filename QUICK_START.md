# 🚀 Quick Start - Pruebas Inmediatas

## ✅ Estado Actual
- ✅ Backend corriendo en **http://localhost:8080**
- ✅ Base de datos con **6 vehículos** y **10 cotizaciones** agrícolas
- ✅ Colección Postman lista para importar

---

## 📝 Paso 1: Postman (5 minutos)

### Importar Colección
1. Abre **Postman**
2. Click **Import** → selecciona archivo:
   ```
   c:\xampp\htdocs\Comuctiva\Comuctiva_String_Boot\POSTMAN_TESTS_GESTRAS.json
   ```

### Pruebas Rápidas
```
1. Login - Obtener Token
   - Ajusta correo/contraseña de tu admin
   - El token se guarda automáticamente

2. GET /api/fletes/config
   - Ver tarifas FURGON y VAN

3. GET /api/vehiculos
   - Ver los 6 vehículos (AGR001-006)

4. GET /api/cotizaciones
   - Ver las 10 cotizaciones de productos agrícolas

5. POST /api/cotizaciones/calcular
   - Calcular cotización sin guardar
   - Ver breakdown de costos

6. PATCH /api/vehiculos/1/estado
   - Cambiar estado a MANTENIMIENTO
```

---

## 🌐 Paso 2: Frontend (5 minutos)

### Iniciar React
```powershell
cd c:\xampp\htdocs\Comuctiva\comuntivaProyecto
npm run dev
```

### Validar en Navegador
1. Abre **http://localhost:5173**
2. Inicia sesión (usuario admin)
3. Navega a **Gestras** (panel de gestión)
4. Verifica:
   - ✅ KPIs de vehículos (disponibles, en ruta, mantenimiento)
   - ✅ Tabla de vehículos con datos reales
   - ✅ Historial de cotizaciones
   - ✅ Modal de detalle con breakdown

---

## 📊 Datos de Referencia

### Vehículos Insertados
| Placa  | Tipo   | Estado        | Conductor       |
|--------|--------|---------------|-----------------|
| AGR001 | FURGON | DISPONIBLE    | Juan Martínez   |
| AGR002 | FURGON | EN_RUTA       | Pedro Ramírez   |
| AGR003 | VAN    | DISPONIBLE    | María González  |
| AGR004 | VAN    | MANTENIMIENTO | Carlos Díaz     |
| AGR005 | FURGON | DISPONIBLE    | Ana Rodríguez   |
| AGR006 | VAN    | EN_RUTA       | Luis Hernández  |

### Productos Agrícolas
- Papa Criolla, Tomate Chonto, Plátano Hartón
- Aguacate Hass, Cebolla Cabezona, Yuca
- Cilantro, Limón Tahití, Zanahoria, Mango Tommy

### Rutas Comunes
- Zipaquirá → Corabastos Bogotá (45 km)
- Girardot → Corabastos Bogotá (134 km)
- La Mesa → Paloquemao Bogotá (62 km)
- Aquitania → Corabastos Bogotá (186 km)

---

## 🔍 Endpoints Principales

```
Base URL: http://localhost:8080

Autenticación:
POST /api/usuario/login

Configuración:
GET /api/fletes/config

Vehículos:
GET    /api/vehiculos
GET    /api/vehiculos/{id}
POST   /api/vehiculos
PUT    /api/vehiculos/{id}
PATCH  /api/vehiculos/{id}/estado
DELETE /api/vehiculos/{id}

Cotizaciones:
GET    /api/cotizaciones
GET    /api/cotizaciones?estado=COMPLETADO
GET    /api/cotizaciones?from=2025-11-01&to=2025-11-30
GET    /api/cotizaciones/{id}
POST   /api/cotizaciones/calcular
POST   /api/cotizaciones
PATCH  /api/cotizaciones/{id}/estado
```

---

## 🐛 Solución Rápida de Problemas

### Backend no responde
```powershell
# Verificar que esté corriendo
# Si no, iniciar:
cd c:\xampp\htdocs\Comuctiva\Comuctiva_String_Boot
.\mvnw.cmd spring-boot:run
```

### 401 Unauthorized en Postman
- Re-ejecutar "Login - Obtener Token"
- Verificar que `{{token}}` tiene valor

### Frontend muestra ceros
- Verificar backend en http://localhost:8080
- Abrir DevTools → Network → ver si hay errores de CORS
- Verificar token en localStorage

---

## 📚 Documentación Completa

Para detalles completos, consulta:
- `GUIA_PRUEBAS_POSTMAN.md` - Guía paso a paso
- `RESUMEN_IMPLEMENTACION_GESTRAS.md` - Resumen técnico completo
- `POSTMAN_TESTS_GESTRAS.json` - Colección de pruebas

---

## ✅ Checklist Final

Antes de commit:
- [ ] Postman: Login funciona
- [ ] Postman: GET /api/vehiculos devuelve 6 vehículos
- [ ] Postman: GET /api/cotizaciones devuelve 10 cotizaciones
- [ ] Frontend: Gestras carga KPIs correctos
- [ ] Frontend: Tabla de vehículos muestra datos
- [ ] Frontend: Modal de detalle funciona

---

**¡Todo listo para probar! 🎉**
