insert into user_account (username, email, password) values ('user1', 'user1@email.com', '123');
insert into user_account (username, email, password) values ('user2', 'user2@email.com', '123');

insert into conference (name, address) values ('Conference 1', '123 Main St');
insert into conference (name, address) values ('Conference 2', '456 Elm St');

insert into session (title, start_date_time, conference_id) values ('Session 1', '2023-10-01 10:00:00', 1);
insert into session (title, start_date_time, conference_id) values ('Session 2', '2023-10-01 11:00:00', 1);
insert into session (title, start_date_time, conference_id) values ('Session 3', '2023-10-02 10:00:00', 2);
insert into session (title, start_date_time, conference_id) values ('Session 4', '2023-10-02 11:00:00', 2);

insert into subscription (user_account_id, session_id, level) values (1, 1, 0);
insert into subscription (user_account_id, session_id, level) values (1, 2, 0);
insert into subscription (user_account_id, session_id, level) values (1, 3, 0);

insert into subscription (user_account_id, session_id, level) values (2, 1, 0);
insert into subscription (user_account_id, session_id, level) values (2, 3, 0);
insert into subscription (user_account_id, session_id, level) values (2, 4, 0);
