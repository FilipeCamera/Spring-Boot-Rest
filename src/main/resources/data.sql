INSERT INTO USERS(id, nome, email, senha) VALUES(1, 'teste', 'teste-fora@email.com', '$2a$12$afiWLw3LMFdTY5P64/xV7uF.E.CIyznpo7rOdKF/H9vKR9DHxVaV2')

INSERT INTO PERFIS(id, nome) VALUES(1, 'ROLE_ADMIN')

INSERT INTO USERS_PERFIS(usuario_id, perfis_id) VALUES(1, 1)
