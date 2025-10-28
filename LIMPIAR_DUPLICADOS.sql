-- ========================================
-- Script para Detectar y Limpiar Usuarios Duplicados
-- ========================================

-- 1. VER USUARIOS DUPLICADOS
SELECT 
    u.ID_TipDocu,
    u.NumDoc,
    COUNT(*) as cantidad,
    GROUP_CONCAT(u.ID_Usuario ORDER BY u.ID_Usuario) as ids_usuarios
FROM usuario u
GROUP BY u.ID_TipDocu, u.NumDoc
HAVING COUNT(*) > 1;

-- 2. VER TODOS LOS USUARIOS CON DETALLE
SELECT 
    ID_Usuario,
    NomUsu,
    NumDoc,
    ID_TipDocu,
    correo
FROM usuario
ORDER BY NumDoc, ID_Usuario;

-- 3. ELIMINAR DUPLICADOS (MANTENER SOLO EL PRIMERO)
-- CUIDADO: Este script eliminará los registros duplicados
-- Primero revisa los resultados de las consultas anteriores

-- Eliminar roles de usuarios duplicados
DELETE FROM rol_usuario 
WHERE ID_Usuario IN (
    SELECT ID_Usuario FROM (
        SELECT 
            u2.ID_Usuario
        FROM usuario u1
        INNER JOIN usuario u2 ON u1.ID_TipDocu = u2.ID_TipDocu 
            AND u1.NumDoc = u2.NumDoc 
            AND u1.ID_Usuario < u2.ID_Usuario
    ) AS duplicados
);

-- Eliminar usuarios duplicados (manteniendo el de menor ID)
DELETE FROM usuario 
WHERE ID_Usuario IN (
    SELECT ID_Usuario FROM (
        SELECT 
            u2.ID_Usuario
        FROM usuario u1
        INNER JOIN usuario u2 ON u1.ID_TipDocu = u2.ID_TipDocu 
            AND u2.NumDoc = u2.NumDoc 
            AND u1.ID_Usuario < u2.ID_Usuario
    ) AS duplicados
);

-- 4. AGREGAR CONSTRAINT UNIQUE PARA EVITAR FUTUROS DUPLICADOS
ALTER TABLE usuario 
ADD CONSTRAINT UK_Usuario_TipDoc_NumDoc 
UNIQUE (ID_TipDocu, NumDoc);

-- 5. VERIFICAR QUE YA NO HAY DUPLICADOS
SELECT 
    u.ID_TipDocu,
    u.NumDoc,
    COUNT(*) as cantidad
FROM usuario u
GROUP BY u.ID_TipDocu, u.NumDoc
HAVING COUNT(*) > 1;
