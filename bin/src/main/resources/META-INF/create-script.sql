CREATE TABLE REMINDER("ID" BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (start with 1, increment by 1), "NAME" VARCHAR(255), "DESCRIPTION" VARCHAR(255),"STATUS" VARCHAR(255),"DUE_DATE" DATE)