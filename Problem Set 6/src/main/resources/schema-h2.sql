create table public.artist (
    artist_id INTEGER,
    artist character varying,
    album_list character varying,
    PRIMARY KEY (artist_id)
);

create table public.album (
    album_id INTEGER PRIMARY KEY,
    title character varying,
    artist_id int,
    date_released date,
    genre character varying,
    number_of_tracks numeric,
    price numeric,
    image_link character varying,
    video_link character varying,
    FOREIGN KEY (artist_id) REFERENCES artist (artist_id)
);

drop sequence public.album_id_seq;

create sequence public.album_id_seq
    increment by 100
    minvalue 1
    maxvalue 2147483647
    start 1
    cache 100;

drop sequence public.artist_id_seq;

create sequence public.artist_id_seq
    increment by 100
    minvalue 1
    maxvalue 2147483647
    start 1
    cache 100;

create table public.application_user (
    user_id INTEGER PRIMARY KEY,
    username character varying ,
    password character varying,
    is_admin boolean
);

drop sequence public.application_user_id_seq;

create sequence public.application_user_id_seq
    increment by 100
    minvalue 1
    maxvalue 2147483647
    start 1
    cache 100;