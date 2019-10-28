create table t_client
(
id numeric,
first_name varchar(100),
last_name varchar(100),
email varchar(100),
 primary key (id)
);
insert into t_client(id, first_name, last_name ,email)
values (1, 'ALI', 'BEN MOHAMED', 'ali.benmohamed@gmail.com') ;
insert into t_client(id, first_name, last_name ,email)
values (2, 'SALAH', 'TEJ', 'salah.tej@gmail.com') ;