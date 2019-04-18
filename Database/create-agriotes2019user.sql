CREATE USER agriotes2019user@localhost IDENTIFIED BY 'agriotes2019pwd';
GRANT ALL ON agriotes2019.* TO agriotes2019user@localhost;
GRANT SELECT ON mysql.proc TO agriotes2019user@localhost;