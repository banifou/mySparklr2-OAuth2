-- Table: sparkluser
CREATE TABLE sparkluser (
  userid serial PRIMARY KEY,
  username character varying(32) NOT NULL,
  passwd character varying(32) NOT NULL
)
WITHOUT OIDS;
ALTER TABLE sparkluser OWNER TO sparkl;

-- Table: userrole
CREATE TABLE userrole (
  roleid serial PRIMARY KEY,
  userid integer NOT NULL,
  userrole character varying(31) NOT NULL,
  CONSTRAINT fk_userrole_userid FOREIGN KEY (userid) REFERENCES sparkluser (userid) ON UPDATE CASCADE ON DELETE SET NULL
)
WITHOUT OIDS;
ALTER TABLE userrole OWNER TO sparkl;

