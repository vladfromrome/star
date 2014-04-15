# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table event)
;

create table key (
  id                        bigint not null,
  keyword                   varchar(255),
  default_value             varchar(255),
  constraint uq_key_keyword unique (keyword),
  constraint pk_key primary key (id))
;

create table language (
  id                        integer not null,
  name                      varchar(255),
  display_name              varchar(255),
  code                      varchar(255),
  constraint uq_language_name unique (name),
  constraint uq_language_code unique (code),
  constraint pk_language primary key (id))
;

create table message (
  id                        bigint not null,
  key_id                    bigint,
  lang_id                   integer,
  value                     varchar(255),
  constraint pk_message primary key (id))
;

create table sport (
  id                        bigint not null,
  tag                       varchar(255),
  constraint uq_sport_tag unique (tag),
  constraint pk_sport primary key (id))
;

create table sport (
  id                        bigint not null,
  tag                       varchar(255),
  constraint uq_sport_tag unique (tag),
  constraint pk_sport primary key (id))
;

create table sport_team (
  id                        bigint not null,
  sys_name                  varchar(255),
  constraint pk_sport_team primary key (id))
;

create table sport_team_translation (
  id                        bigint not null,
  team_id                   bigint,
  lang                      varchar(255),
  name                      varchar(255),
  constraint pk_sport_team_translation primary key (id))
;

create table subject (
  id                        bigint not null,
  tag                       varchar(255),
  constraint uq_subject_tag unique (tag),
  constraint pk_subject primary key (id))
;

create table translation (
  id                        bigint not null,
  label                     varchar(255),
  language_id               integer,
  constraint pk_translation primary key (id))
;

create table translation (
  id                        integer not null,
  label                     varchar(255),
  language_id               integer,
  constraint pk_translation primary key (id))
;


create table sport_translation (
  sport_id                       bigint not null,
  translation_id                 integer not null,
  constraint pk_sport_translation primary key (sport_id, translation_id))
;

create table subject_translation (
  subject_id                     bigint not null,
  translation_id                 integer not null,
  constraint pk_subject_translation primary key (subject_id, translation_id))
;

create table translation_sport (
  translation_id                 integer not null,
  sport_id                       bigint not null,
  constraint pk_translation_sport primary key (translation_id, sport_id))
;
create sequence key_seq;

create sequence language_seq;

create sequence message_seq;

create sequence sport_seq;

create sequence sport_seq;

create sequence sport_team_seq;

create sequence sport_team_translation_seq;

create sequence subject_seq;

create sequence translation_seq;

create sequence translation_seq;

alter table message add constraint fk_message_key_1 foreign key (key_id) references key (id);
create index ix_message_key_1 on message (key_id);
alter table message add constraint fk_message_lang_2 foreign key (lang_id) references language (id);
create index ix_message_lang_2 on message (lang_id);
alter table sport_team_translation add constraint fk_sport_team_translation_team_3 foreign key (team_id) references sport_team (id);
create index ix_sport_team_translation_team_3 on sport_team_translation (team_id);
alter table translation add constraint fk_translation_language_4 foreign key (language_id) references language (id);
create index ix_translation_language_4 on translation (language_id);
alter table translation add constraint fk_translation_language_5 foreign key (language_id) references language (id);
create index ix_translation_language_5 on translation (language_id);



alter table sport_translation add constraint fk_sport_translation_sport_01 foreign key (sport_id) references sport (id);

alter table sport_translation add constraint fk_sport_translation_translat_02 foreign key (translation_id) references translation (id);

alter table subject_translation add constraint fk_subject_translation_subjec_01 foreign key (subject_id) references subject (id);

alter table subject_translation add constraint fk_subject_translation_transl_02 foreign key (translation_id) references translation (id);

alter table translation_sport add constraint fk_translation_sport_translat_01 foreign key (translation_id) references translation (id);

alter table translation_sport add constraint fk_translation_sport_sport_02 foreign key (sport_id) references sport (id);

# --- !Downs

drop table if exists event cascade;

drop table if exists key cascade;

drop table if exists language cascade;

drop table if exists message cascade;

drop table if exists sport cascade;

drop table if exists sport_translation cascade;

drop table if exists sport cascade;

drop table if exists sport_team cascade;

drop table if exists sport_team_translation cascade;

drop table if exists subject cascade;

drop table if exists subject_translation cascade;

drop table if exists translation cascade;

drop table if exists translation cascade;

drop table if exists translation_sport cascade;

drop sequence if exists key_seq;

drop sequence if exists language_seq;

drop sequence if exists message_seq;

drop sequence if exists sport_seq;

drop sequence if exists sport_seq;

drop sequence if exists sport_team_seq;

drop sequence if exists sport_team_translation_seq;

drop sequence if exists subject_seq;

drop sequence if exists translation_seq;

drop sequence if exists translation_seq;

