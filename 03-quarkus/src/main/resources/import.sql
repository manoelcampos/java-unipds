-- Como a classe Person tem um ID com GenerationType.IDENTITY
-- o tipo do campo já vai ser definido como auto_increment, no lugar
-- de usar um sequence no banco para gerar o valor para o ID.
-- Assim, nem mesmo precisa informar o ID nos inserts.
insert into person (name, birthdate) values ('John', '2024-01-02');
insert into person (name, birthdate) values ('Maria', '2000-03-13');
insert into person (name, birthdate) values ('Carla', '2024-03-13');
