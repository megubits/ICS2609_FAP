# ICS2609_FAP
FAP for 2609

DB Populate Code:

INSERT INTO ARTISTS VALUES('ARTIST_1', 'My Chemical Romance');
INSERT INTO ARTISTS VALUES('ARTIST_2', 'Paramore');
INSERT INTO ARTISTS VALUES('ARTIST_3', 'Fall Out Boy');
INSERT INTO ARTISTS VALUES('ARTIST_4', 'Adele');
INSERT INTO ARTISTS VALUES('ARTIST_5', 'Ed Sheeran');
INSERT INTO ARTISTS VALUES('ARTIST_6', 'Taylor Swift');

INSERT INTO SONGLIST VALUES('SONG_1', 'Welcome to the Black Parade', 'ARTIST_1');
INSERT INTO SONGLIST VALUES('SONG_2', 'Still Into You', 'ARTIST_2');
INSERT INTO SONGLIST VALUES('SONG_3', 'Centuries', 'ARTIST_3');
INSERT INTO SONGLIST VALUES('SONG_4', 'Hello', 'ARTIST_4');
INSERT INTO SONGLIST VALUES('SONG_5', 'Shape of You', 'ARTIST_5');
INSERT INTO SONGLIST VALUES('SONG_6', 'ME!', 'ARTIST_6');

INSERT INTO USERS VALUES('USER_1', 'user', 'TJxIGTFznH6HuMuy3as6Hg==');
INSERT INTO USERS VALUES('USER_2', 'member', 'TJxIGTFznH6HuMuy3as6Hg==');

SELECT * FROM APP.ARTISTS FETCH FIRST 120 ROWS ONLY;
SELECT * FROM APP.SONGLIST FETCH FIRST 120 ROWS ONLY;
SELECT * FROM APP.USERS FETCH FIRST 120 ROWS ONLY;
