insert into naucna_oblast (id, naziv) values ("1","Hemija");
insert into naucna_oblast (id, naziv) values ("2","Racunarstvo");
insert into naucna_oblast (id, naziv) values ("3","Matematika");

insert into nacin_placanja (id, nacin) values ("1","VisaCard");
insert into nacin_placanja (id, nacin) values ("2","PayPal");
insert into nacin_placanja (id, nacin) values ("3","MasterCard");

insert into casopis(id,naziv,issn,aktivan) values("2","Nautika","12321",true);
insert into naucne_oblasti_cas(casopis_id,naucna_oblast_id) values("2","1");
insert into naucne_oblasti_cas(casopis_id,naucna_oblast_id) values("2","3");

insert into korisnik (id,ime,prezime,grad,drzava,titula,email,username,password,recezent,akt_kod,aktiviran,hoce_rec, uredniknocas_id, glavni_ured_cas_id,non_locked) values ("1","Milan","Katic","NS","SRB","Dipl ing","katicmilan7@gmail.com","katic7","$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe",true,"DSDA",true,true,null,null,1);
insert into korisnik (id,ime,prezime,grad,drzava,titula,email,username,password,recezent,akt_kod,aktiviran,hoce_rec, uredniknocas_id, glavni_ured_cas_id,non_locked) values ("2","Marko","Markovic","BG","SRB","MS","katicmilan@yahoo.com","mark","$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe",false,"DSDA",true,false,null,"2",1);
insert into korisnik (id,ime,prezime,grad,drzava,titula,email,username,password,recezent,akt_kod,aktiviran,hoce_rec, uredniknocas_id, glavni_ured_cas_id,non_locked) values ("3","Petar","Petrovic","BG","SRB","MS","naucnacentrala2020@gmail.com","pera","$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe",false,"DSDA",true,false,"2",null,1);
insert into korisnik (id,ime,prezime,grad,drzava,titula,email,username,password,recezent,akt_kod,aktiviran,hoce_rec, uredniknocas_id, glavni_ured_cas_id,non_locked) values ("4","Jovan","Jovanovic","BG","SRB","MS","d2497752@urhen.com","jova","$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe",false,"DSDA",true,false,null,null,1);
insert into korisnik (id,ime,prezime,grad,drzava,titula,email,username,password,recezent,akt_kod,aktiviran,hoce_rec, uredniknocas_id, glavni_ured_cas_id,non_locked) values ("5","Nikola","Nikolic","BG","SRB","MS","ivana96b@gmail.com","nidza","$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe",false,"DSDA",true,false,null,null,1);



insert into korisnik_oblasti (korisnik_id,oblast_id) values ("3", "1");
insert into korisnik_oblasti (korisnik_id,oblast_id) values ("3", "2");
insert into korisnik_oblasti (korisnik_id,oblast_id) values ("3", "3");
insert into korisnik_oblasti (korisnik_id,oblast_id) values ("4", "1");
insert into korisnik_oblasti (korisnik_id,oblast_id) values ("4", "2");
insert into korisnik_oblasti (korisnik_id,oblast_id) values ("5", "1");

insert into recezenti_casopisa (casopis_id, recez_id) values ("2", "4");
insert into recezenti_casopisa (casopis_id, recez_id) values ("2", "5");



-- Permissions
insert into permission (id, name) values (1, 'CREATE');
insert into permission (id, name) values (2, 'READ');

-- Roles
insert into roles (id, name) values (1, 'USER');
insert into roles (id, name) values (2, 'REVIEWER');
insert into roles (id, name) values (3, 'EDITOR');
insert into roles (id, name) values (4, 'ADMIN');

-- Korisnici i Role
insert into korisnik_roles (korisnik_id, role_id) values (1, 3);


