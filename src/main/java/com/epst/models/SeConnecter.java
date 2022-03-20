package com.epst.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class SeConnecter {

    public Connection con;
    //
    ResultSet résultats = null;
    //

    public SeConnecter(){
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            con = DriverManager.getConnection(dbUrl);
            //con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/agent_epst", "postgres", "joellungu");
            Statement stmt = con.createStatement();
            //
            String sql = "" +
                    "--\n" +
                    "            -- PostgreSQL database dump\n" +
                    "            --\n" +
                    "            \n" +
                    "            -- Dumped from database version 12.10\n" +
                    "            -- Dumped by pg_dump version 12.10\n" +
                    "            \n" +
                    "            -- Started on 2022-03-20 16:53:12\n" +
                    "            \n" +
                    "            SET statement_timeout = 0;\n" +
                    "            SET lock_timeout = 0;\n" +
                    "            SET idle_in_transaction_session_timeout = 0;\n" +
                    "            SET client_encoding = 'UTF8';\n" +
                    "            SET standard_conforming_strings = on;\n" +
                    "            SELECT pg_catalog.set_config('search_path', '', false);\n" +
                    "            SET check_function_bodies = false;\n" +
                    "            SET xmloption = content;\n" +
                    "            SET client_min_messages = warning;\n" +
                    "            SET row_security = off;\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 5 (class 2615 OID 2200)\n" +
                    "            -- Name: public; Type: SCHEMA; Schema: -; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            CREATE SCHEMA public;\n" +
                    "            \n" +
                    "            \n" +
                    "            ALTER SCHEMA public OWNER TO postgres;\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 2923 (class 0 OID 0)\n" +
                    "            -- Dependencies: 5\n" +
                    "            -- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            COMMENT ON SCHEMA public IS 'standard public schema';\n" +
                    "            \n" +
                    "            \n" +
                    "            SET default_tablespace = '';\n" +
                    "            \n" +
                    "            SET default_table_access_method = heap;\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 224 (class 1259 OID 16572)\n" +
                    "            -- Name: agent_epst; Type: TABLE; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            CREATE TABLE public.agent_epst (\n" +
                    "                id integer NOT NULL,\n" +
                    "                nom character varying,\n" +
                    "                postnom character varying,\n" +
                    "                prenom character varying,\n" +
                    "                date_de_naissance timestamp without time zone,\n" +
                    "                numero character varying,\n" +
                    "                email character varying,\n" +
                    "                adresse character varying,\n" +
                    "                role integer,\n" +
                    "                matricule character varying\n" +
                    "            );\n" +
                    "            \n" +
                    "            \n" +
                    "            ALTER TABLE public.agent_epst OWNER TO postgres;\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 231 (class 1259 OID 16637)\n" +
                    "            -- Name: chat; Type: TABLE; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            CREATE TABLE public.chat (\n" +
                    "                id integer,\n" +
                    "                date date,\n" +
                    "                agent integer,\n" +
                    "                correspondant character varying,\n" +
                    "                telephone character varying,\n" +
                    "                statut character varying\n" +
                    "            );\n" +
                    "            \n" +
                    "            \n" +
                    "            ALTER TABLE public.chat OWNER TO postgres;\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 232 (class 1259 OID 16643)\n" +
                    "            -- Name: conversation; Type: TABLE; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            CREATE TABLE public.conversation (\n" +
                    "                id integer,\n" +
                    "                id_chat integer,\n" +
                    "                conversation text\n" +
                    "            );\n" +
                    "            \n" +
                    "            \n" +
                    "            ALTER TABLE public.conversation OWNER TO postgres;\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 229 (class 1259 OID 16625)\n" +
                    "            -- Name: magasin; Type: TABLE; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            CREATE TABLE public.magasin (\n" +
                    "                id integer,\n" +
                    "                libelle character varying,\n" +
                    "                description text,\n" +
                    "                date_mise_en_ligne date,\n" +
                    "                agent_mise_en_ligne integer,\n" +
                    "                piece_jointe bytea\n" +
                    "            );\n" +
                    "            \n" +
                    "            \n" +
                    "            ALTER TABLE public.magasin OWNER TO postgres;\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 226 (class 1259 OID 16595)\n" +
                    "            -- Name: piece_jointe; Type: TABLE; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            CREATE TABLE public.piece_jointe (\n" +
                    "                id integer,\n" +
                    "                id_piece_jointe integer,\n" +
                    "                fichier bytea\n" +
                    "            );\n" +
                    "            \n" +
                    "            \n" +
                    "            ALTER TABLE public.piece_jointe OWNER TO postgres;\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 225 (class 1259 OID 16588)\n" +
                    "            -- Name: plainte; Type: TABLE; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            CREATE TABLE public.plainte (\n" +
                    "                id integer,\n" +
                    "                envoyeur character varying,\n" +
                    "                email_envoyeur character varying,\n" +
                    "                destinateur character varying,\n" +
                    "                provinve_envoyeur character varying,\n" +
                    "                type_tiquet integer,\n" +
                    "                message text\n" +
                    "            );\n" +
                    "            \n" +
                    "            \n" +
                    "            ALTER TABLE public.plainte OWNER TO postgres;\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 230 (class 1259 OID 16631)\n" +
                    "            -- Name: reforme; Type: TABLE; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            CREATE TABLE public.reforme (\n" +
                    "                id integer,\n" +
                    "                libelle character varying,\n" +
                    "                description text,\n" +
                    "                date_de_mise_en_ligne date,\n" +
                    "                user_mise_en_ligne integer,\n" +
                    "                piece_jointe bytea\n" +
                    "            );\n" +
                    "            \n" +
                    "            \n" +
                    "            ALTER TABLE public.reforme OWNER TO postgres;\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 227 (class 1259 OID 16601)\n" +
                    "            -- Name: statut; Type: TABLE; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            CREATE TABLE public.statut (\n" +
                    "                id integer,\n" +
                    "                statut character varying,\n" +
                    "                code_statut integer\n" +
                    "            );\n" +
                    "            \n" +
                    "            \n" +
                    "            ALTER TABLE public.statut OWNER TO postgres;\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 228 (class 1259 OID 16604)\n" +
                    "            -- Name: tiquet; Type: TABLE; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            CREATE TABLE public.tiquet (\n" +
                    "                id integer,\n" +
                    "                libelle character varying,\n" +
                    "                code_tiquet integer\n" +
                    "            );\n" +
                    "            \n" +
                    "            \n" +
                    "            ALTER TABLE public.tiquet OWNER TO postgres;\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 2909 (class 0 OID 16572)\n" +
                    "            -- Dependencies: 224\n" +
                    "            -- Data for Name: agent_epst; Type: TABLE DATA; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            COPY public.agent_epst (id, nom, postnom, prenom, date_de_naissance, numero, email, adresse, role, matricule) FROM stdin;\n" +
                    "            \\.\n" +
                    "            \n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 2916 (class 0 OID 16637)\n" +
                    "            -- Dependencies: 231\n" +
                    "            -- Data for Name: chat; Type: TABLE DATA; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            COPY public.chat (id, date, agent, correspondant, telephone, statut) FROM stdin;\n" +
                    "            \\.\n" +
                    "            \n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 2917 (class 0 OID 16643)\n" +
                    "            -- Dependencies: 232\n" +
                    "            -- Data for Name: conversation; Type: TABLE DATA; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            COPY public.conversation (id, id_chat, conversation) FROM stdin;\n" +
                    "            \\.\n" +
                    "            \n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 2914 (class 0 OID 16625)\n" +
                    "            -- Dependencies: 229\n" +
                    "            -- Data for Name: magasin; Type: TABLE DATA; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            COPY public.magasin (id, libelle, description, date_mise_en_ligne, agent_mise_en_ligne, piece_jointe) FROM stdin;\n" +
                    "            \\.\n" +
                    "            \n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 2911 (class 0 OID 16595)\n" +
                    "            -- Dependencies: 226\n" +
                    "            -- Data for Name: piece_jointe; Type: TABLE DATA; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            COPY public.piece_jointe (id, id_piece_jointe, fichier) FROM stdin;\n" +
                    "            \\.\n" +
                    "            \n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 2910 (class 0 OID 16588)\n" +
                    "            -- Dependencies: 225\n" +
                    "            -- Data for Name: plainte; Type: TABLE DATA; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            COPY public.plainte (id, envoyeur, email_envoyeur, destinateur, provinve_envoyeur, type_tiquet, message) FROM stdin;\n" +
                    "            \\.\n" +
                    "            \n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 2915 (class 0 OID 16631)\n" +
                    "            -- Dependencies: 230\n" +
                    "            -- Data for Name: reforme; Type: TABLE DATA; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            COPY public.reforme (id, libelle, description, date_de_mise_en_ligne, user_mise_en_ligne, piece_jointe) FROM stdin;\n" +
                    "            \\.\n" +
                    "            \n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 2912 (class 0 OID 16601)\n" +
                    "            -- Dependencies: 227\n" +
                    "            -- Data for Name: statut; Type: TABLE DATA; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            COPY public.statut (id, statut, code_statut) FROM stdin;\n" +
                    "            \\.\n" +
                    "            \n" +
                    "            \n" +
                    "            --\n" +
                    "            -- TOC entry 2913 (class 0 OID 16604)\n" +
                    "            -- Dependencies: 228\n" +
                    "            -- Data for Name: tiquet; Type: TABLE DATA; Schema: public; Owner: postgres\n" +
                    "            --\n" +
                    "            \n" +
                    "            COPY public.tiquet (id, libelle, code_tiquet) FROM stdin;\n" +
                    "            \\.\n" +
                    "            \n" +
                    "            \n" +
                    "            -- Completed on 2022-03-20 16:53:12\n" +
                    "            \n" +
                    "            --\n" +
                    "            -- PostgreSQL database dump complete\n" +
                    "            --\n" +
                    "            \n" +
                    "            ";

         stmt.executeUpdate(sql);
         //
        }catch(Exception ex){
            System.out.println("Erreur du à: "+ex.getMessage());
        }
    }
}
