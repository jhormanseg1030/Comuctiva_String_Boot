# 🚀 Guía de Pruebas - Backend Comuctiva Gestras

## 📋 Resumen de Cambios Implementados

### ✅ Base de Datos
- **Tabla `transportadora`**: Limpiada y alineada (telefono VARCHAR, nombres en minúsculas)
- **Tabla `vehiculo`**: Creada con FK a `transportadora.id_transpor`
- **Tabla `cotizacion`**: Creada con JSON detalles, FK a transportadora
- **Tabla `config_fletes`**: Creada con tarifas por tipo de vehículo
- **Datos semilla**: 1 transportadora (Comuctiva), 6 vehículos, 10 cotizaciones agrícolas

### ✅ Backend (Spring Boot)
- **Entidades alineadas**: `@Column` y `@JoinColumn` corregidos para coincidir con BD
  - `Vehiculo.tipo` → `tipo_vehiculo`
  - `Cotizacion.pesoKg` → `peso_kg`
  - `Cotizacion.distanciaKm` → `distancia_km`
  - FKs: `id_transpor` (no `id_transportadora`)
- **Endpoints listos**:
  - `/api/vehiculos` (GET, POST, PUT, PATCH, DELETE)
  - `/api/cotizaciones` (GET, POST, PATCH)
  - `/api/fletes/config` (GET)

---

## 🧪 Pruebas con Postman

### 1️⃣ Importar Colección
1. Abre Postman
2. Click en **Import** → **File**
3. Selecciona: `c:\xampp\htdocs\Comuctiva\Comuctiva_String_Boot\POSTMAN_TESTS_GESTRAS.json`
4. La colección "Comuctiva - Gestión de Transporte Agrícola" aparecerá

### 2️⃣ Configurar Variables
- `baseUrl`: `http://localhost:8080` (ya configurado)
- `token`: Se guarda automáticamente al hacer login

### 3️⃣ Flujo de Pruebas Recomendado

#### A. Autenticación
1. **Login - Obtener Token**
   - Ajusta el body con un usuario administrador de tu BD
   - Ejemplo: `{"correo": "admin@comuctiva.com", "contrasena": "admin123"}`
   - El token se guarda automáticamente en variable `{{token}}`

#### B. Configuración (sin autenticación requerida)
2. **GET - Obtener Configuración Tarifas**
   - Endpoint: `GET /api/fletes/config`
   - Respuesta esperada:
   ```json
   {
     "vehiculos": {
       "FURGON": {
         "tarifaBase": 50000,
         "costoKm": 1500,
         "maxDistancia": 500,
         "capacidadKg": 3000,
         "descripcion": "Furgón refrigerado..."
       },
       "VAN": {
         "tarifaBase": 35000,
         "costoKm": 1100,
         "maxDistancia": 400,
         "capacidadKg": 1500,
         "descripcion": "Van urbana..."
       }
     },
     "reglas": {
       "seguroPct": 2.0,
       "ivaPct": 19.0,
       "peajeEstimadoPorKm": 200
     }
   }
   ```

#### C. Vehículos
3. **GET - Listar Todos los Vehículos**
   - Endpoint: `GET /api/vehiculos`
   - Debe devolver los 6 vehículos sembrados (AGR001-AGR006)
   - Validar: `tipo`, `placa`, `estado`, `viajesMes`, `ingresosMes`

4. **GET - Obtener Vehículo por ID**
   - Endpoint: `GET /api/vehiculos/1`
   - Debe devolver el Furgón Refrigerado 1 (AGR001)

5. **POST - Crear Nuevo Vehículo**
   - Endpoint: `POST /api/vehiculos`
   - Body ya incluido en la colección
   - Validar: status 201, `id` generado

6. **PATCH - Cambiar Estado Vehículo**
   - Endpoint: `PATCH /api/vehiculos/1/estado`
   - Cambiar a `MANTENIMIENTO`
   - Validar: campo `estado` actualizado

7. **PUT - Actualizar Vehículo**
   - Endpoint: `PUT /api/vehiculos/6`
   - Modificar capacidad, ubicación, KPIs
   - Validar: todos los campos actualizados

8. **DELETE - Eliminar Vehículo** (opcional)
   - Endpoint: `DELETE /api/vehiculos/7`
   - Si creaste el vehículo en paso 5, elimínalo

#### D. Cotizaciones
9. **GET - Listar Todas las Cotizaciones**
   - Endpoint: `GET /api/cotizaciones`
   - Debe devolver las 10 cotizaciones sembradas
   - Validar: productos agrícolas, estados, totales

10. **GET - Filtrar por Estado**
    - Endpoint: `GET /api/cotizaciones?estado=COMPLETADO`
    - Debe devolver solo cotizaciones completadas (5 registros)

11. **GET - Filtrar por Fechas**
    - Endpoint: `GET /api/cotizaciones?from=2025-11-01&to=2025-11-30`
    - Ajusta fechas según tu caso

12. **GET - Obtener Cotización por ID**
    - Endpoint: `GET /api/cotizaciones/1`
    - Validar: `detalles` con breakdown (tarifaBase, costoKm, seguro, iva, peaje, total)

13. **POST - Calcular Cotización (sin guardar)**
    - Endpoint: `POST /api/cotizaciones/calcular`
    - Body: Banano Premium, 2000kg, FURGON, 780km
    - Validar: devuelve cálculo completo sin persistir

14. **POST - Crear Cotización**
    - Endpoint: `POST /api/cotizaciones`
    - Body: Café en Grano (ya incluido)
    - Validar: status 201, `detalles` calculados automáticamente

15. **PATCH - Actualizar Estado**
    - Endpoint: `PATCH /api/cotizaciones/5/estado`
    - Cambiar a `EN_PROCESO`

16. **PATCH - Rechazar Cotización**
    - Endpoint: `PATCH /api/cotizaciones/8/estado`
    - Estado `RECHAZADO` + `motivoRechazo`

#### E. Test de Integración
17. **Test Completo - Crear Cotización**
    - Endpoint: `POST /api/cotizaciones`
    - Incluye validaciones automáticas (Tests en Postman)
    - Verifica status 201 y presencia de `id`

---

## 🔍 Validaciones Clave

### Vehículos
- ✅ Tipos: `FURGON`, `VAN`
- ✅ Estados: `DISPONIBLE`, `EN_RUTA`, `MANTENIMIENTO`
- ✅ Placas únicas (formato ABC123)
- ✅ FK `id_transpor` apunta a Comuctiva (id=1)

### Cotizaciones
- ✅ Detalles JSON con breakdown completo
- ✅ Estados: `PENDIENTE`, `EN_PROCESO`, `COMPLETADO`, `RECHAZADO`
- ✅ Cálculo automático de costos al crear
- ✅ Productos agrícolas realistas

### Configuración
- ✅ Tarifas diferenciadas por tipo
- ✅ Reglas globales (seguro 2%, IVA 19%, peaje/km)

---

## 🐛 Errores Comunes

### 401 Unauthorized
- **Causa**: Token expirado o no incluido
- **Solución**: Re-ejecutar "Login - Obtener Token"

### 404 Not Found
- **Causa**: Endpoint incorrecto o ID inexistente
- **Solución**: Verificar URL y que el ID existe en BD

### 400 Bad Request
- **Causa**: Validaciones fallidas (campos obligatorios, formato inválido)
- **Solución**: Revisar body JSON, asegurar:
  - `tipo`, `tipoVehiculo`: valores enum válidos
  - `placa`: formato ABC123
  - `pesoKg`, `distanciaKm`: números positivos

### 500 Internal Server Error
- **Causa**: FK inválida, error de mapeo entidad-BD
- **Solución**: Verificar logs del backend, comprobar:
  - `transportadoraId` existe en tabla `transportadora`
  - Nombres de columnas coinciden entre entidad y BD

---

## 📊 Datos de Prueba Sugeridos

### Nuevos Vehículos
```json
{
  "tipo": "FURGON",
  "nombre": "Furgón Carga Refrigerada 3",
  "placa": "AGR007",
  "conductor": "Roberto Sánchez",
  "capacidadKg": 3500,
  "estado": "DISPONIBLE",
  "mantenimiento": false,
  "ubicacion": "Medellín - Bodega Principal",
  "viajesMes": 0,
  "ingresosMes": 0,
  "transportadoraId": 1
}
```

### Nuevas Cotizaciones
```json
{
  "producto": "Naranja Valencia - 80 canastillas",
  "pesoKg": 1200,
  "tipoVehiculo": "VAN",
  "origen": "Fresno",
  "destino": "Corabastos Bogotá",
  "distanciaKm": 198,
  "estado": "PENDIENTE",
  "transportadoraId": 1
}
```

---

## 🎯 Checklist de Validación

- [ ] Backend arranca sin errores (puerto 8080)
- [ ] Login devuelve token válido
- [ ] GET `/api/fletes/config` devuelve configuración
- [ ] GET `/api/vehiculos` devuelve 6 vehículos
- [ ] POST `/api/vehiculos` crea vehículo nuevo
- [ ] PATCH `/api/vehiculos/{id}/estado` actualiza estado
- [ ] GET `/api/cotizaciones` devuelve 10 cotizaciones
- [ ] POST `/api/cotizaciones/calcular` devuelve breakdown
- [ ] POST `/api/cotizaciones` crea y calcula automáticamente
- [ ] PATCH `/api/cotizaciones/{id}/estado` actualiza estado
- [ ] Filtros por fecha y estado funcionan
- [ ] Frontend Gestras.jsx carga datos (siguiente paso)

---

## 🚀 Siguiente Paso: Frontend

Una vez validado el backend:
1. Inicia el frontend React: `npm run dev`
2. Navega a **Gestras** (panel de gestión)
3. Verifica que:
   - **KPIs de vehículos** se cargan (disponibles, en ruta, mantenimiento)
   - **Tabla de vehículos** muestra datos reales
   - **Historial de cotizaciones** carga con filtros
   - **Modal de detalle** muestra breakdown de costos
   - **Configuración de tarifas** se aplica correctamente

---

## 📝 Notas Finales

- **Seguridad**: Todos los endpoints (excepto `/api/fletes/config`) requieren autenticación JWT
- **Roles**: Crear/actualizar/eliminar vehículos requiere rol **Administrador**
- **Persistencia**: Las cotizaciones calculadas (`/calcular`) NO se guardan; usa `/api/cotizaciones` POST para persistir
- **Logs**: Revisa logs del backend en la terminal para errores de mapeo o validación

---

**¡Éxito en las pruebas! 🎉**
