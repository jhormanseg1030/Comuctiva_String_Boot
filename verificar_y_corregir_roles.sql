-- ========================================
-- VERIFICAR Y CORREGIR ROLES DE USUARIOS
-- ========================================

-- 1. Ver los roles actuales de todos los usuarios
SELECT 
    u.ID_Usuario,
    u.NomUsu,
    u.NumDoc,
    r.Nom_Rol
FROM usuario u
LEFT JOIN usuario_roles ur ON u.ID_Usuario = ur.usuario_id
LEFT JOIN rol r ON ur.rol_id = r.id_rol
ORDER BY u.ID_Usuario;

-- 2. Ver si existe la tabla usuario_roles
SHOW TABLES LIKE 'usuario_roles';

-- 3. Ver estructura de usuario_roles
DESCRIBE usuario_roles;

-- 4. Ver todos los roles disponibles
SELECT * FROM rol;

-- ========================================
-- ASIGNAR ROLES A LOS USUARIOS (SI FALTAN)
-- ========================================

-- Asignar rol "Cliente" (id_rol = 2) a Ana (ID_Usuario = 2)
INSERT IGNORE INTO usuario_roles (usuario_id, rol_id) 
VALUES (2, 2);

-- Asignar rol "Cliente" a Pedro (ID_Usuario = 3)
INSERT IGNORE INTO usuario_roles (usuario_id, rol_id) 
VALUES (3, 2);

-- Asignar rol "Cliente" a Laura (ID_Usuario = 4)
INSERT IGNORE INTO usuario_roles (usuario_id, rol_id) 
VALUES (4, 2);

-- Asignar rol "Cliente" a Juan (ID_Usuario = 5)
INSERT IGNORE INTO usuario_roles (usuario_id, rol_id) 
VALUES (5, 2);

-- Verificar que se asignaron correctamente
SELECT 
    u.ID_Usuario,
    u.NomUsu,
    u.NumDoc,
    r.Nom_Rol
FROM usuario u
INNER JOIN usuario_roles ur ON u.ID_Usuario = ur.usuario_id
INNER JOIN rol r ON ur.rol_id = r.id_rol
WHERE u.ID_Usuario IN (2, 3, 4, 5);
