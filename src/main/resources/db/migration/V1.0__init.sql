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


--

CREATE TABLE main."dayOfWeek"
(
    id serial NOT NULL,
    "dayOfWeek" text NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "dayOfWeekUnique" UNIQUE ("dayOfWeek")
);

insert into main."dayOfWeek" ("dayOfWeek") values ('понедельник');
insert into main."dayOfWeek" ("dayOfWeek") values ('вторник');
insert into main."dayOfWeek" ("dayOfWeek") values ('среда');
insert into main."dayOfWeek" ("dayOfWeek") values ('четверг');
insert into main."dayOfWeek" ("dayOfWeek") values ('пятница');
insert into main."dayOfWeek" ("dayOfWeek") values ('суббота');
insert into main."dayOfWeek" ("dayOfWeek") values ('воскресенье');

CREATE TABLE main."typeSubject"
(
    id serial NOT NULL,
    "typeSubject" text NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "typeSubjectUnique" UNIQUE ("typeSubject")
);

insert into main."typeSubject" ("typeSubject") values ('Лекция');
insert into main."typeSubject" ("typeSubject") values ('Лабораторная работа');
insert into main."typeSubject" ("typeSubject") values ('Практическая работа');

CREATE TABLE main."Subject"
(
    id serial NOT NULL,
    name text NOT NULL,
    "countClass" smallint NOT NULL,
    "typeSubject" smallint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "nameUnique" UNIQUE (name),
    CONSTRAINT "typeSubjectFK" FOREIGN KEY ("typeSubject")
        REFERENCES main."typeSubject" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

CREATE TABLE main."Equipment"
(
    id serial NOT NULL,
    name text NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "nameEquipmentUnique" UNIQUE (name)
);

insert into main."Equipment" ("name") values ('Компьютеры');
insert into main."Equipment" ("name") values ('Доска');
insert into main."Equipment" ("name") values ('Проектор');

CREATE TABLE main."SubjectEquiepment"
(
    "Subjectid" smallint NOT NULL,
    "Equipmentid" smallint NOT NULL,
    PRIMARY KEY ("Subjectid", "Equipmentid"),
    CONSTRAINT "equipmentidFK" FOREIGN KEY ("Equipmentid")
        REFERENCES main."Equipment" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT "SubjectidFK" FOREIGN KEY ("Subjectid")
        REFERENCES main."Subject" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS main."Subject"
    ADD COLUMN "countClassPerWeek" smallint NOT NULL;

CREATE TABLE main."Teacher"
(
    id serial NOT NULL,
    name text NOT NULL,
    subjectid smallint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "subjectTeacherFK" FOREIGN KEY (subjectid)
        REFERENCES main."Subject" (id) MATCH SIMPLE
        ON UPDATE SET NULL
        ON DELETE SET NULL
        NOT VALID
);

CREATE TABLE main."TeacherTypeSubject"
(
    "TypeSubjectid" smallint NOT NULL,
    "Teacherid" smallint NOT NULL,
    PRIMARY KEY ("TypeSubjectid", "Teacherid"),
    CONSTRAINT "TypeSubjectidFK" FOREIGN KEY ("TypeSubjectid")
        REFERENCES main."typeSubject" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "TeacheridFK" FOREIGN KEY ("Teacherid")
        REFERENCES main."Teacher" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE main."Group"
(
    id serial NOT NULL,
    "numberGroup" smallint NOT NULL,
    "countStudents" smallint NOT NULL,
    "numberOfCourse" smallint NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE main."Audience"
(
    id serial NOT NULL,
    "numberAudience" smallint NOT NULL,
    capacity smallint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "nameAudienceUnique" UNIQUE ("numberAudience")
);

CREATE TABLE main."AudienceEquipment"
(
    "Audienceid" smallint NOT NULL,
    "Equipmentid" smallint NOT NULL,
    PRIMARY KEY ("Audienceid", "Equipmentid"),
    CONSTRAINT "AudienceidFK" FOREIGN KEY ("Audienceid")
        REFERENCES main."Audience" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT "AEquipmentidFK" FOREIGN KEY ("Equipmentid")
        REFERENCES main."Equipment" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);





