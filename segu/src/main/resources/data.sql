-- Insert users with ALUM role
INSERT INTO usuarios (username, password, email, rol) 
VALUES ('estudiante1', 'password123', 'estudiante1@example.com', 'ROL_ALUM');

-- Insert users with PROF role
INSERT INTO usuarios (username, password, email, rol) 
VALUES ('profesor1', 'password123', 'profesor1@example.com', 'ROL_PROF');

-- Insert user with both roles
INSERT INTO usuarios (username, password, email, rol) 
VALUES ('admin', 'password123', 'admin@example.com', 'ROL_ALUM,ROL_PROF');

-- Insert user with no role
INSERT INTO usuarios (username, password, email, rol) 
VALUES ('usuario', 'password123', 'usuario@example.com', NULL);

