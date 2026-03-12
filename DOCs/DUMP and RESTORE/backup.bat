echo "Backup do banco Union iniciado..."
cd %MYSQL_HOME%\bin
mysqldump -h 127.0.0.1 -u root --password=root portalescolarv4 --result-file=C:\temp\portalescolarv4_%date:~-10,2%-%date:~-7,2%-%date:~-4,4%-%time:~0,2%_%time:~3,2%_%time:~6,2%.sql --add-locks --extended-insert --lock-tables --disable-keys --tz-utc --comments --create-options --quote-names
pause;
