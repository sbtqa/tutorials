--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 9.6.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE account (
    accid bigint NOT NULL,
    balance integer,
    firstname character varying(255),
    lastname character varying(255)
);


ALTER TABLE account OWNER TO postgres;

--
-- Name: account_accid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE account_accid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE account_accid_seq OWNER TO postgres;

--
-- Name: account_accid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE account_accid_seq OWNED BY account.accid;


--
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE transaction (
    trxid bigint NOT NULL,
    amount integer,
    from_id bigint,
    to_id bigint
);


ALTER TABLE transaction OWNER TO postgres;

--
-- Name: transaction_trxid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE transaction_trxid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE transaction_trxid_seq OWNER TO postgres;

--
-- Name: transaction_trxid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE transaction_trxid_seq OWNED BY transaction.trxid;


--
-- Name: account accid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account ALTER COLUMN accid SET DEFAULT nextval('account_accid_seq'::regclass);


--
-- Name: transaction trxid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transaction ALTER COLUMN trxid SET DEFAULT nextval('transaction_trxid_seq'::regclass);


--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY account (accid, balance, firstname, lastname) FROM stdin;
4	4100000	Petr	Petrov
5	31000	Oskar	Guera
6	50000000	Damir	Mirzanurov
7	52000	Andrey	Kochemasov
87	0	Vladimir	Ivanov
88	2000	Ekaterina	Popova
90	1000	Vladimir	Ivanov
91	1000	Vladimir	Ivanov
92	1000	Ekaterina	Popova
93	0	Vladimir	Ivanov
94	2000	Ekaterina	Popova
96	1000	Vladimir	Ivanov
1	29997	Tatyana	Voteva
2	20003	Andrey	Sokolov
18	1000	Vladimir	Ivanov
19	1000	Vladimir	Ivanov
20	1000	Ekaterina	Popova
21	0	Vladimir	Ivanov
22	2000	Ekaterina	Popova
24	1000	Vladimir	Ivanov
25	1000	Vladimir	Ivanov
26	1000	Ekaterina	Popova
27	0	Vladimir	Ivanov
28	2000	Ekaterina	Popova
30	1000	Vladimir	Ivanov
31	1000	Vladimir	Ivanov
32	1000	Ekaterina	Popova
33	0	Vladimir	Ivanov
34	2000	Ekaterina	Popova
36	1000	Vladimir	Ivanov
37	1000	Vladimir	Ivanov
38	1000	Ekaterina	Popova
39	0	Vladimir	Ivanov
40	2000	Ekaterina	Popova
42	1000	Vladimir	Ivanov
43	1000	Vladimir	Ivanov
44	1000	Ekaterina	Popova
45	0	Vladimir	Ivanov
46	2000	Ekaterina	Popova
48	1000	Vladimir	Ivanov
49	1000	Vladimir	Ivanov
50	1000	Ekaterina	Popova
51	0	Vladimir	Ivanov
52	2000	Ekaterina	Popova
54	1000	Vladimir	Ivanov
55	1000	Vladimir	Ivanov
56	1000	Ekaterina	Popova
57	0	Vladimir	Ivanov
58	2000	Ekaterina	Popova
60	1000	Vladimir	Ivanov
61	1000	Vladimir	Ivanov
62	1000	Ekaterina	Popova
63	0	Vladimir	Ivanov
64	2000	Ekaterina	Popova
66	1000	Vladimir	Ivanov
67	1000	Vladimir	Ivanov
68	1000	Ekaterina	Popova
69	0	Vladimir	Ivanov
70	2000	Ekaterina	Popova
72	1000	Vladimir	Ivanov
73	1000	Vladimir	Ivanov
74	1000	Ekaterina	Popova
75	0	Vladimir	Ivanov
76	2000	Ekaterina	Popova
78	1000	Vladimir	Ivanov
79	1000	Vladimir	Ivanov
80	1000	Ekaterina	Popova
81	0	Vladimir	Ivanov
82	2000	Ekaterina	Popova
84	1000	Vladimir	Ivanov
85	1000	Vladimir	Ivanov
86	1000	Ekaterina	Popova
97	1000	Vladimir	Ivanov
98	1000	Ekaterina	Popova
99	0	Vladimir	Ivanov
100	2000	Ekaterina	Popova
102	1000	Vladimir	Ivanov
103	1000	Vladimir	Ivanov
104	1000	Ekaterina	Popova
105	0	Vladimir	Ivanov
106	2000	Ekaterina	Popova
108	1000	Vladimir	Ivanov
109	1000	Vladimir	Ivanov
110	1000	Ekaterina	Popova
111	0	Vladimir	Ivanov
112	2000	Ekaterina	Popova
114	1000	Vladimir	Ivanov
115	1000	Vladimir	Ivanov
116	1000	Ekaterina	Popova
117	0	Vladimir	Ivanov
118	2000	Ekaterina	Popova
120	1000	Vladimir	Ivanov
121	1000	Vladimir	Ivanov
122	1000	Ekaterina	Popova
123	0	Vladimir	Ivanov
124	2000	Ekaterina	Popova
126	1000	Vladimir	Ivanov
127	1000	Vladimir	Ivanov
128	1000	Ekaterina	Popova
129	0	Vladimir	Ivanov
130	2000	Ekaterina	Popova
132	1000	Vladimir	Ivanov
133	1000	Vladimir	Ivanov
134	1000	Ekaterina	Popova
135	0	Vladimir	Ivanov
136	2000	Ekaterina	Popova
138	1000	Vladimir	Ivanov
139	1000	Vladimir	Ivanov
140	1000	Ekaterina	Popova
141	0	Vladimir	Ivanov
142	2000	Ekaterina	Popova
144	1000	Vladimir	Ivanov
145	1000	Vladimir	Ivanov
146	1000	Ekaterina	Popova
147	0	Vladimir	Ivanov
148	2000	Ekaterina	Popova
150	1000	Vladimir	Ivanov
151	1000	Vladimir	Ivanov
152	1000	Ekaterina	Popova
153	0	Vladimir	Ivanov
154	2000	Ekaterina	Popova
156	1000	Vladimir	Ivanov
157	1000	Vladimir	Ivanov
158	1000	Ekaterina	Popova
159	0	Vladimir	Ivanov
160	2000	Ekaterina	Popova
162	1000	Vladimir	Ivanov
163	1000	Vladimir	Ivanov
164	1000	Ekaterina	Popova
165	0	Vladimir	Ivanov
166	2000	Ekaterina	Popova
168	1000	Vladimir	Ivanov
169	1000	Vladimir	Ivanov
170	1000	Ekaterina	Popova
171	0	Vladimir	Ivanov
172	2000	Ekaterina	Popova
174	1000	Vladimir	Ivanov
175	1000	Vladimir	Ivanov
176	1000	Ekaterina	Popova
177	0	Vladimir	Ivanov
178	2000	Ekaterina	Popova
180	1000	Vladimir	Ivanov
181	1000	Vladimir	Ivanov
182	1000	Ekaterina	Popova
183	0	Vladimir	Ivanov
184	2000	Ekaterina	Popova
186	1000	Vladimir	Ivanov
187	1000	Vladimir	Ivanov
188	1000	Ekaterina	Popova
189	0	Vladimir	Ivanov
190	2000	Ekaterina	Popova
192	1000	Vladimir	Ivanov
193	1000	Vladimir	Ivanov
194	1000	Ekaterina	Popova
195	0	Vladimir	Ivanov
196	2000	Ekaterina	Popova
198	1000	Vladimir	Ivanov
199	1000	Vladimir	Ivanov
200	1000	Ekaterina	Popova
201	0	Vladimir	Ivanov
202	2000	Ekaterina	Popova
204	1000	Vladimir	Ivanov
205	1000	Vladimir	Ivanov
206	1000	Ekaterina	Popova
207	0	Vladimir	Ivanov
208	2000	Ekaterina	Popova
210	1000	Vladimir	Ivanov
211	1000	Vladimir	Ivanov
212	1000	Ekaterina	Popova
213	0	Vladimir	Ivanov
214	2000	Ekaterina	Popova
216	1000	Vladimir	Ivanov
217	1000	Vladimir	Ivanov
218	1000	Ekaterina	Popova
219	0	Vladimir	Ivanov
220	2000	Ekaterina	Popova
222	1000	Vladimir	Ivanov
223	1000	Vladimir	Ivanov
224	1000	Ekaterina	Popova
225	0	Vladimir	Ivanov
226	2000	Ekaterina	Popova
228	1000	Vladimir	Ivanov
229	1000	Vladimir	Ivanov
230	1000	Ekaterina	Popova
231	0	Vladimir	Ivanov
232	2000	Ekaterina	Popova
234	1000	Vladimir	Ivanov
235	1000	Vladimir	Ivanov
236	1000	Ekaterina	Popova
237	0	Vladimir	Ivanov
238	2000	Ekaterina	Popova
\.


--
-- Name: account_accid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('account_accid_seq', 238, true);


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY transaction (trxid, amount, from_id, to_id) FROM stdin;
1	1000	1	2
2	1	1	2
3	1	1	2
4	1000	14	15
5	1	1	2
6	1	1	2
7	1000	21	22
8	1000	27	28
9	1000	33	34
10	1000	39	40
11	1000	45	46
12	1000	51	52
13	1000	57	58
14	1000	63	64
15	1000	69	70
16	1000	75	76
17	1000	81	82
18	1000	87	88
19	1000	93	94
20	1000	99	100
21	1000	105	106
22	1000	111	112
23	1000	117	118
24	1000	123	124
25	1000	129	130
26	1000	135	136
27	1000	141	142
28	1000	147	148
29	1000	153	154
30	1000	159	160
31	1000	165	166
32	1000	171	172
33	1000	177	178
34	1000	183	184
35	1000	189	190
36	1000	195	196
37	1000	201	202
38	1000	207	208
39	1000	213	214
40	1000	219	220
41	1000	225	226
42	1000	231	232
43	1000	237	238
\.


--
-- Name: transaction_trxid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('transaction_trxid_seq', 43, true);


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_pkey PRIMARY KEY (accid);


--
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (trxid);


--
-- PostgreSQL database dump complete
--

