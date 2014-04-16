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
  sportId                   bigint not null,
  tag                       varchar(255),
  constraint uq_sport_tag unique (tag),
  constraint pk_sport primary key (sportId))
;

create table subject (
  subjId                    bigint not null,
  tag                       varchar(255),
  constraint uq_subject_tag unique (tag),
  constraint pk_subject primary key (subjId))
;

create table translation (
  id                        bigint not null,
  label                     varchar(255),
  language_id               integer,
  constraint pk_translation primary key (id))
;


create table Sport_Translation (
  sport_sportId                  bigint not null,
  translation_id                 bigint not null,
  constraint pk_Sport_Translation primary key (sport_sportId, translation_id))
;

create table Subject_Translation (
  subject_subjId                 bigint not null,
  translation_id                 bigint not null,
  constraint pk_Subject_Translation primary key (subject_subjId, translation_id))
;
create sequence key_seq;

create sequence language_seq;

create sequence message_seq;

create sequence sport_seq;

create sequence subject_seq;

create sequence translation_seq;

alter table message add constraint fk_message_key_1 foreign key (key_id) references key (id);
create index ix_message_key_1 on message (key_id);
alter table message add constraint fk_message_lang_2 foreign key (lang_id) references language (id);
create index ix_message_lang_2 on message (lang_id);
alter table translation add constraint fk_translation_language_3 foreign key (language_id) references language (id);
create index ix_translation_language_3 on translation (language_id);



alter table Sport_Translation add constraint fk_Sport_Translation_sport_01 foreign key (sport_sportId) references sport (sportId);

alter table Sport_Translation add constraint fk_Sport_Translation_translat_02 foreign key (translation_id) references translation (id);

alter table Subject_Translation add constraint fk_Subject_Translation_subjec_01 foreign key (subject_subjId) references subject (subjId);

alter table Subject_Translation add constraint fk_Subject_Translation_transl_02 foreign key (translation_id) references translation (id);

# --- !Downs

drop table if exists key cascade;

drop table if exists language cascade;

drop table if exists message cascade;

drop table if exists sport cascade;

drop table if exists Sport_Translation cascade;

drop table if exists subject cascade;

drop table if exists Subject_Translation cascade;

drop table if exists translation cascade;

drop sequence if exists key_seq;

drop sequence if exists language_seq;

drop sequence if exists message_seq;

drop sequence if exists sport_seq;

drop sequence if exists subject_seq;

drop sequence if exists translation_seq;

