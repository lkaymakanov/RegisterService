
--postgre create table directly mapping to xls file
CREATE TABLE if not exists turreg
(
  turreg_id numeric,
  nomer bigint,  
  municipality character varying,
  municipality_id numeric,
  udostovereniesrok character varying,
  kind character varying,
  type1 character varying,
  subtype character varying, 
  name1 character varying,
  category character varying,
  capacity character varying,
  capacityindoor character varying,
  capacityoutdoor character varying,
  roomcnt character varying,
  bedcnt character varying,
  province character varying,
  city character varying,  -
  address character varying,
  personincharge character varying,
  personcontact character varying,
  tel character varying,
  fax character varying,
  email character varying,
  regno character varying,
  recordcreated timestamp,
  recordchanges timestamp,
  xls_id character varying NOT NULL,   -- the unique identifier
  unique (xls_id),
  CONSTRAINT pk_turreg PRIMARY KEY (turreg_id)
  
);



create table if not exists MUNICIPALITYXLS
(
  MUNICIPALITYXLS_ID    numeric not null,
  PROVINCE_ID           numeric,
  FULLNAME1             character varying, --VARCHAR2(100 CHAR),
  NAME1                 character varying, -- VARCHAR2(40 CHAR),
  CODE                  character varying, -- VARCHAR2(16 CHAR),
  EBK_CODE              character varying, --VARCHAR2(8 CHAR),
  OLD_CODE              character varying, --VARCHAR2(10 CHAR),
  URL                   character varying, --VARCHAR2(200 CHAR),
  PARENTMUNICIPALITYXLS_ID numeric,
  CONSTRAINT pk_MUNICIPALITY_ID PRIMARY KEY (MUNICIPALITYXLS_ID)
);




create table if not exists municipality_xls_mapper
(
  MUNICIPALITY_ID       numeric not null,
  FULLNAME1             character varying
);

/**=============================REGULAR NOTICE SCRIPT======================================================
/*
 * 
 * 
  *   
  CREATE SEQUENCE if not exists s_regularnoticedebt
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
 
    CREATE SEQUENCE if not exists s_regularnoticeobject
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
 
     CREATE SEQUENCE if not exists s_regularnotice
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
  CREATE SEQUENCE if not exists s_regularnoticedocno
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
 
drop table regularnoticedebt;
drop table regularnoticeobject;
drop table regularnotice;

create table if not exists regularnotice
(

 regularnotice_id   numeric primary key,
 user_id            numeric, 
 docno              character varying, 
 municipality_id    numeric,
 taxsubject_id      numeric,
 noticedate         timestamp,
 
 constraint fk_regularnotice_user foreign key (user_id)
 references users (user_id),
 constraint fk_regularnotice_municipality foreign key (municipality_id)
 references municipality (municipality_id),
 constraint fk_regularnotice_taxsubject foreign key (taxsubject_id)
 references taxsubject (taxsubject_id)

);

create table if not exists regularnoticeobject
(
 regularnoticeobject_id   numeric primary key,
 regularnotice_id         numeric,
 taxobject_id             numeric,
 partidano                character varying,
 objectdescription        character varying,
 
 CONSTRAINT fk_regularnoticeobject_regularnotice FOREIGN KEY (regularnotice_id)
 REFERENCES regularnotice (regularnotice_id)
 
);

create table if not exists regularnoticedebt
(
 regularnoticedebt_id numeric primary key,
 regularnoticeobject_id  numeric,
 debtinstalment_id numeric, 
 interestsum  numeric(22,5),
 discsum      numeric(22,5),
 intbegindate timestamp,

 CONSTRAINT fk_regularnoticedebt_debtinstalment FOREIGN KEY (debtinstalment_id)
 REFERENCES debtinstalment (debtinstalment_id)

);
/**=============================END OF REGULAR NOTICE SCRIPT======================================================
*/



/*
 * 
 *   CREATE SEQUENCE if not exists s_turreg
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
 */

/*
 CREATE OR REPLACE FUNCTION public.cretesequence(IN  schmema_name character varying, 
 IN sequence_name character varying,
 in startval bigint,
 in incrementval bigint,
 in cahceval bigint
 
)
  RETURNS character varying AS
$BODY$
declare
 _kind "char";
 retval character varying;
begin
 retval:= 'OK';
 SELECT INTO _kind  c.relkind
 FROM   pg_class     c
 JOIN   pg_namespace n ON n.oid = c.relnamespace
 WHERE  c.relname = sequence_name   -- sequence name here
 AND    n.nspname = schmema_name    -- schema name here
 AND    c.relkind = ANY('{r,i,S,v,f}');
  
 IF NOT FOUND THEN       -- name is free
    CREATE SEQUENCE  s_turreg
  	 INCREMENT 1
  	 MINVALUE 1
 	 MAXVALUE 9223372036854775807
  	 START 1
  	 CACHE 1;
 ELSIF _kind = 'S' THEN  -- sequence exists
  retval:= 'EXISTS';
 ELSE                    -- conflicting object of different type exists
   -- do somethng!
   retval:='CONFLICT';
 END IF;

  return retval;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  */
