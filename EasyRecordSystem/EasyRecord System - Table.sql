DROP TABLE er_entry CASCADE CONSTRAINTS;

DROP SEQUENCE er_entryid_seq;

--Entry Table
CREATE TABLE er_entry (
    Entry_ID number(10),
    Entry_Date Date NOT NULL,
    Category_Type varchar2(20) NOT NULL,  
    Event_Detail varchar2(30) NOT NULL,
    pay_account varchar2(20) NOT NULL,
    pay_amount number(10,2) NOT NULL,
    CONSTRAINT pk_entry PRIMARY KEY(Entry_ID));

--Customer Sequence
CREATE SEQUENCE er_entryid_seq 
    INCREMENT BY 1
    START WITH 1;

--Date Indexe
CREATE INDEX entry_date_idx
ON er_entry (Entry_Date);

--Category Indexe
CREATE INDEX entry_category_idx
ON er_entry (Category_Type);

COMMIT;

