
CREATE TABLE IF NOT EXISTS public.director
(
    id bigint NOT NULL,
    fio text NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.movie
(
    id bigint NOT NULL,
    title text NOT NULL,
    year integer NOT NULL,
    director bigint NOT NULL,
    length time without time zone NOT NULL,
    rating integer NOT NULL,
    PRIMARY KEY (id)
    );

ALTER TABLE IF EXISTS public.movie
    ADD FOREIGN KEY (director)
    REFERENCES public.director (id);