Drop table  if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255)  PRIMARY KEY ,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information TEXT,
  create_time timestamp default now(),
  archived tinyint(1) default '0',
  trusted tinyint(1) default '0',
  autoapprove VARCHAR (255) default 'false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into oauth_client_details
(client_id,resource_ids,client_secret,scope,authorized_grant_types,authorities,web_server_redirect_uri)
 values("client_1","order","123456","select","client_credentials,refresh_token","client","http://localhost:8005"),
("client_2","order","123456","select","password,refresh_token","client","http://localhost:8005");

select  * from oauth_client_details;

Drop table  if exists oauth_access_token;
create table oauth_access_token (
  create_time timestamp default now(),
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255),
  index token_id_index  (token_id),
  index authentication_id_index (authentication_id),
  index user_name_index (user_name),
  index client_id_index (client_id),
  index refresh_token_index (refresh_token)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

select * from oauth_access_token;

Drop table  if exists oauth_refresh_token;
create table oauth_refresh_token (
  create_time timestamp default now(),
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB,
  index token_id_index  (token_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
select * from oauth_refresh_token;

Drop table  if exists oauth_code;
create table oauth_code (
  create_time timestamp default now(),
  code VARCHAR(255),
  authentication BLOB,
  index code_index (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

select * from oauth_code;



