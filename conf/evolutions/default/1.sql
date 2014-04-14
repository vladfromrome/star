# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table key (
  id                        bigint not null,
  keyword                   varchar(255),
  default_value             varchar(255),
  constraint uq_key_keyword unique (keyword),
  constraint pk_key primary key (id))
;

create table language (
  id                        bigint not null,
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
  lang_id                   bigint,
  value                     varchar(255),
  constraint pk_message primary key (id))
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

create sequence key_seq;

create sequence language_seq;

create sequence message_seq;

create sequence sport_team_seq;

create sequence sport_team_translation_seq;

alter table message add constraint fk_message_key_1 foreign key (key_id) references key (id);
create index ix_message_key_1 on message (key_id);
alter table message add constraint fk_message_lang_2 foreign key (lang_id) references language (id);
create index ix_message_lang_2 on message (lang_id);
alter table sport_team_translation add constraint fk_sport_team_translation_team_3 foreign key (team_id) references sport_team (id);
create index ix_sport_team_translation_team_3 on sport_team_translation (team_id);



# --- !Downs

drop table if exists key cascade;

drop table if exists language cascade;

drop table if exists message cascade;

drop table if exists sport_team cascade;

drop table if exists sport_team_translation cascade;

drop sequence if exists key_seq;

drop sequence if exists language_seq;

drop sequence if exists message_seq;

drop sequence if exists sport_team_seq;

drop sequence if exists sport_team_translation_seq;

