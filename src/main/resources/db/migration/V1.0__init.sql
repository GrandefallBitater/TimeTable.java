create table users(
  username varchar(50) not null,
  password varchar(100) not null,
  enabled Boolean not null,
  primary key(username)
);

insert into users values
('rinat', '{noop}123', true),
('ruslan', '{noop}321', true),
('user', '{noop}123', true);

create table authorities(
    username varchar(50) not null,
    authority varchar(50) not null,

    constraint authorities_idx unique(username, authority),

    constraint authorities_ibfk_1
    foreign key(username)
    references users(username)
    on update cascade
);

insert into authorities values
('rinat', 'ROLE_ADMIN'),
('ruslan', 'ROLE_ADMIN'),
('user', 'ROLE_USER');