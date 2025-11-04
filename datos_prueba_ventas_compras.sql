-- ========================================
-- DATOS DE PRUEBA PARA VENTAS Y COMPRAS
-- Sistema COMUCTIVA
-- ========================================

-- IMPORTANTE: Ejecutar este script DESPUÉS de haber creado las tablas de roles
-- y asignado productos a los vendedores.

-- ========================================
-- ESCENARIO 1: Ana compra Manzana del Admin
-- ========================================

-- 1. Crear pedido para Ana (ID_Usuario: 2)
INSERT INTO pedidos (cant_pedido, fec_pedido, ID_Usuario) 
VALUES (3, NOW(), 2);

SET @pedido_ana_1 = LAST_INSERT_ID();

-- 2. Crear compra asociada al pedido
INSERT INTO compra (fec_com, ID_Pedido, ID_Estado, ID_Direccion, ID_MetodoPago, ID_Transportadora, ID_descuento)
VALUES (NOW(), @pedido_ana_1, 1, 1, 1, 1, NULL);

SET @compra_ana_1 = LAST_INSERT_ID();

-- 3. Registrar productos comprados (Ana compra 3 Manzanas del Admin - Producto ID: 1)
INSERT INTO comp_produc (cantidad, Precio, ID_Compra, ID_Producto)
VALUES (3, 2500.00, @compra_ana_1, 1);

-- ========================================
-- ESCENARIO 2: Pedro compra Lechuga de Ana
-- ========================================

-- 4. Crear pedido para Pedro (ID_Usuario: 3)
INSERT INTO pedidos (cant_pedido, fec_pedido, ID_Usuario) 
VALUES (5, NOW(), 3);

SET @pedido_pedro_1 = LAST_INSERT_ID();

-- 5. Crear compra asociada al pedido
INSERT INTO compra (fec_com, ID_Pedido, ID_Estado, ID_Direccion, ID_MetodoPago, ID_Transportadora, ID_descuento)
VALUES (NOW(), @pedido_pedro_1, 1, 1, 1, 1, NULL);

SET @compra_pedro_1 = LAST_INSERT_ID();

-- 6. Registrar productos (Pedro compra 5 Lechugas de Ana - Producto ID: 2)
INSERT INTO comp_produc (cantidad, Precio, ID_Compra, ID_Producto)
VALUES (5, 1500.00, @compra_pedro_1, 2);

-- ========================================
-- ESCENARIO 3: Juan compra Tomate de Ana
-- ========================================

-- 7. Crear pedido para Juan (ID_Usuario: 5)
INSERT INTO pedidos (cant_pedido, fec_pedido, ID_Usuario) 
VALUES (10, NOW(), 5);

SET @pedido_juan_1 = LAST_INSERT_ID();

-- 8. Crear compra asociada al pedido
INSERT INTO compra (fec_com, ID_Pedido, ID_Estado, ID_Direccion, ID_MetodoPago, ID_Transportadora, ID_descuento)
VALUES (NOW(), @pedido_juan_1, 1, 1, 1, 1, NULL);

SET @compra_juan_1 = LAST_INSERT_ID();

-- 9. Registrar productos (Juan compra 10 Tomates de Ana - Producto ID: 3)
INSERT INTO comp_produc (cantidad, Precio, ID_Compra, ID_Producto)
VALUES (10, 2000.00, @compra_juan_1, 3);

-- ========================================
-- ESCENARIO 4: Ana compra Frijol de Juan
-- ========================================

-- 10. Crear otro pedido para Ana
INSERT INTO pedidos (cant_pedido, fec_pedido, ID_Usuario) 
VALUES (2, NOW(), 2);

SET @pedido_ana_2 = LAST_INSERT_ID();

-- 11. Crear compra asociada
INSERT INTO compra (fec_com, ID_Pedido, ID_Estado, ID_Direccion, ID_MetodoPago, ID_Transportadora, ID_descuento)
VALUES (NOW(), @pedido_ana_2, 1, 1, 1, 1, NULL);

SET @compra_ana_2 = LAST_INSERT_ID();

-- 12. Ana compra 2kg de Frijol de Juan (Producto ID: 7)
INSERT INTO comp_produc (cantidad, Precio, ID_Compra, ID_Producto)
VALUES (2, 3000.00, @compra_ana_2, 7);

-- ========================================
-- VERIFICAR LOS DATOS CREADOS
-- ========================================

SELECT 
    '==============================================' as separador;
SELECT 'RESUMEN DE COMPRAS CREADAS' as titulo;
SELECT 
    '==============================================' as separador;

SELECT 
    cp.ID_Compra as 'ID Compra',
    p.Nom_Produc as 'Producto',
    CONCAT(u_cliente.NomUsu, ' ', u_cliente.apell1) as 'Cliente (Comprador)',
    CONCAT(u_vendedor.NomUsu, ' ', IFNULL(u_vendedor.apell1, '')) as 'Vendedor',
    cp.cantidad as 'Cantidad',
    CONCAT('$', FORMAT(cp.Precio, 0)) as 'Precio Unit.',
    CONCAT('$', FORMAT(cp.cantidad * cp.Precio, 0)) as 'Total',
    DATE_FORMAT(c.fec_com, '%d/%m/%Y %H:%i') as 'Fecha'
FROM comp_produc cp
JOIN producto p ON cp.ID_Producto = p.ID_Producto
JOIN compra c ON cp.ID_Compra = c.ID_Compra
JOIN pedidos ped ON c.ID_Pedido = ped.ID_Pedido
JOIN usuario u_cliente ON ped.ID_Usuario = u_cliente.ID_Usuario
LEFT JOIN usuario u_vendedor ON p.ID_Usuario = u_vendedor.ID_Usuario
ORDER BY c.fec_com DESC;

SELECT 
    '==============================================' as separador;
SELECT 'RESUMEN POR USUARIO' as titulo;
SELECT 
    '==============================================' as separador;

-- Ventas por vendedor
SELECT 
    CONCAT(u.NomUsu, ' ', IFNULL(u.apell1, '')) as 'Vendedor',
    COUNT(DISTINCT cp.ID_Compra) as 'Total Ventas',
    CONCAT('$', FORMAT(SUM(cp.cantidad * cp.Precio), 0)) as 'Total Vendido'
FROM usuario u
LEFT JOIN producto p ON u.ID_Usuario = p.ID_Usuario
LEFT JOIN comp_produc cp ON p.ID_Producto = cp.ID_Producto
WHERE u.ID_Usuario IN (1, 2, 5) -- Admin, Ana, Juan (vendedores)
GROUP BY u.ID_Usuario, u.NomUsu, u.apell1
ORDER BY SUM(cp.cantidad * cp.Precio) DESC;

SELECT 
    '==============================================' as separador;

-- Compras por cliente
SELECT 
    CONCAT(u.NomUsu, ' ', IFNULL(u.apell1, '')) as 'Cliente',
    COUNT(DISTINCT c.ID_Compra) as 'Total Compras',
    CONCAT('$', FORMAT(SUM(cp.cantidad * cp.Precio), 0)) as 'Total Gastado'
FROM usuario u
LEFT JOIN pedidos ped ON u.ID_Usuario = ped.ID_Usuario
LEFT JOIN compra c ON ped.ID_Pedido = c.ID_Pedido
LEFT JOIN comp_produc cp ON c.ID_Compra = cp.ID_Compra
WHERE u.ID_Usuario IN (2, 3, 5) -- Ana, Pedro, Juan (compradores)
GROUP BY u.ID_Usuario, u.NomUsu, u.apell1
ORDER BY SUM(cp.cantidad * cp.Precio) DESC;

SELECT 
    '==============================================' as separador;
SELECT 'DATOS DE PRUEBA CREADOS EXITOSAMENTE' as resultado;
SELECT 
    '==============================================' as separador;

-- ========================================
-- PRÓXIMOS PASOS PARA PROBAR
-- ========================================
/*
AHORA PRUEBA LOS ENDPOINTS EN POSTMAN:

1. LOGIN COMO ANA (numDoc: 55555555, password: 1234)
   POST http://localhost:8080/api/usuario/login
   
   Resultado esperado en /api/mis-ventas:
   - 2 ventas (Lechuga a Pedro, Tomate a Juan)
   
   Resultado esperado en /api/mis-compras:
   - 2 compras (Manzana del Admin, Frijol de Juan)

2. LOGIN COMO PEDRO (numDoc: 33333333, password: 1234)
   POST http://localhost:8080/api/usuario/login
   
   Resultado esperado en /api/mis-ventas:
   - 0 ventas (no tiene productos asignados)
   
   Resultado esperado en /api/mis-compras:
   - 1 compra (Lechuga de Ana)

3. LOGIN COMO JUAN (numDoc: 11111111, password: 1234)
   POST http://localhost:8080/api/usuario/login
   
   Resultado esperado en /api/mis-ventas:
   - 1 venta (Frijol a Ana)
   
   Resultado esperado en /api/mis-compras:
   - 1 compra (Tomate de Ana)

4. LOGIN COMO ADMIN (numDoc: 22222222, password: admin123)
   POST http://localhost:8080/api/usuario/login
   
   Resultado esperado en /api/mis-ventas:
   - 1 venta (Manzana a Ana)
   
   Resultado esperado en /api/mis-compras:
   - 0 compras
*/
