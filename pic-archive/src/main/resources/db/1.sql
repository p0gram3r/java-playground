create table files (
	id INTEGER IDENTITY,
	path VARCHAR(500) NOT NULL UNIQUE
);

create table urls (
	id INTEGER IDENTITY,
	url varchar(500) NOT NULL UNIQUE,
	status varchar(10) DEFAULT 'NEW' NOT NULL check (status in ('NEW', 'ERROR', 'DONE')),
	file_id INTEGER REFERENCES files(id)
);
