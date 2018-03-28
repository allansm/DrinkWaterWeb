/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Allan de souza melo
 * Created: 24/03/2018
 */
create database drinkwater;
use drinkwater;
create table tbl_users(
    id bigint not null auto_increment primary key,
    login varchar(50) unique,
    password varchar(32)
)engine = innoDB;
create table tbl_cups(
    id bigint not null auto_increment primary key,
    times smallint,
    user_id bigint not null,
    cupDate varchar(255) not null,
    cupTime varchar(255) not null
)engine = innoDB;
alter table tbl_cups
add foreign key(user_id)
references tbl_users(id)
on delete no action
on update no action;