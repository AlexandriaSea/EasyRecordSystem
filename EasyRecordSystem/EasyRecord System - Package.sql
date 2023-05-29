--Package Specification--
CREATE OR REPLACE PACKAGE easyrecord_pkg
IS    
    --Add Entry Procedure--
    PROCEDURE add_entry_pp
        (e_date IN er_entry.entry_date%TYPE,
        e_category IN er_entry.category_type%TYPE,
        e_detail IN er_entry.event_detail%TYPE,
        e_account IN er_entry.pay_account%TYPE,
        e_amount IN er_entry.pay_amount%TYPE);
    
    --Search Entry Procedure--
    PROCEDURE search_entry_pp
        (e_id IN er_entry.entry_id%TYPE,
        e_date OUT er_entry.entry_date%TYPE,
        e_category OUT er_entry.category_type%TYPE,
        e_detail OUT er_entry.event_detail%TYPE,
        e_account OUT er_entry.pay_account%TYPE,
        e_amount OUT er_entry.pay_amount%TYPE);
    
    --Update Entry Procedure--
    PROCEDURE update_entry_pp
        (e_id IN er_entry.entry_id%TYPE,
        e_date IN er_entry.entry_date%TYPE,
        e_category IN er_entry.category_type%TYPE,
        e_detail IN er_entry.event_detail%TYPE,
        e_account IN er_entry.pay_account%TYPE,
        e_amount IN er_entry.pay_amount%TYPE);
END;





--Package Body--
CREATE OR REPLACE PACKAGE BODY easyrecord_pkg
IS
    --Add Entry Procedure--
    PROCEDURE add_entry_pp
        (e_date IN er_entry.entry_date%TYPE,
        e_category IN er_entry.category_type%TYPE,
        e_detail IN er_entry.event_detail%TYPE,
        e_account IN er_entry.pay_account%TYPE,
        e_amount IN er_entry.pay_amount%TYPE)
    IS
    BEGIN
        INSERT INTO er_entry
        VALUES (er_entryid_seq.NEXTVAL, e_date, e_category, e_detail, e_account, e_amount);
    END add_entry_pp;
    
    --Search Entry Procedure--
    PROCEDURE search_entry_pp
        (e_id IN er_entry.entry_id%TYPE,
        e_date OUT er_entry.entry_date%TYPE,
        e_category OUT er_entry.category_type%TYPE,
        e_detail OUT er_entry.event_detail%TYPE,
        e_account OUT er_entry.pay_account%TYPE,
        e_amount OUT er_entry.pay_amount%TYPE)
    IS
    BEGIN
        SELECT entry_date, category_type, event_detail, pay_account, pay_amount
        INTO e_date, e_category, e_detail, e_account, e_amount 
        FROM er_entry
        WHERE entry_id = e_id; 
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No record.');    
    END search_entry_pp;
    
    --Update Entry Procedure--
    PROCEDURE update_entry_pp
        (e_id IN er_entry.entry_id%TYPE,
        e_date IN er_entry.entry_date%TYPE,
        e_category IN er_entry.category_type%TYPE,
        e_detail IN er_entry.event_detail%TYPE,
        e_account IN er_entry.pay_account%TYPE,
        e_amount IN er_entry.pay_amount%TYPE)
    IS
    BEGIN
        UPDATE er_entry
        SET entry_date = e_date,
            category_type = e_category,
            event_detail = e_detail,
            pay_account = e_account,
            pay_amount = e_amount
        WHERE entry_id = e_id;
    END update_entry_pp;
    
    --Display Year Entry Procedure--
    PROCEDURE display_year_entry_pp
        (e_year IN VARCHAR2,
        e_id OUT er_entry.entry_id%TYPE,
        e_date OUT er_entry.entry_date%TYPE,
        e_category OUT er_entry.category_type%TYPE,
        e_detail OUT er_entry.event_detail%TYPE,
        e_account OUT er_entry.pay_account%TYPE,
        e_amount OUT er_entry.pay_amount%TYPE)
    IS
    BEGIN
        SELECT entry_id, entry_date, category_type, event_detail, pay_account, pay_amount
        INTO e_id, e_date, e_category, e_detail, e_account, e_amount 
        FROM er_entry
        WHERE TO_CHAR(entry_date, 'YYYY') = e_year; 
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No record.');
    END display_year_entry_pp; 
    
    --Display Month Entry Procedure--
    PROCEDURE display_month_entry_pp
        (e_year IN VARCHAR2,
        e_month IN VARCHAR2,
        e_id OUT er_entry.entry_id%TYPE,
        e_date OUT er_entry.entry_date%TYPE,
        e_category OUT er_entry.category_type%TYPE,
        e_detail OUT er_entry.event_detail%TYPE,
        e_account OUT er_entry.pay_account%TYPE,
        e_amount OUT er_entry.pay_amount%TYPE)
    IS
    BEGIN
        SELECT entry_id, entry_date, category_type, event_detail, pay_account, pay_amount
        INTO e_id, e_date, e_category, e_detail, e_account, e_amount 
        FROM er_entry
        WHERE TO_CHAR(entry_date, 'YYYY') = e_year
        AND TO_CHAR(entry_date, 'MM') = e_month; 
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No record.');
    END display_month_entry_pp;
    
    --Display Year Sum Procedure--
    PROCEDURE display_year_sum_pp
        (e_year OUT VARCHAR2,
        e_year_sum OUT er_entry.pay_amount%TYPE)
    IS
    BEGIN
        SELECT TO_CHAR(entry_date, 'YYYY'), SUM(pay_amount)
        INTO e_year, e_year_sum
        FROM er_entry
        GROUP BY TO_CHAR(entry_date, 'YYYY'); 
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No record.');
    END display_year_sum_pp;
    
    --Display Month Sum Procedure--
    PROCEDURE display_month_sum_pp
        (e_year IN VARCHAR2,
        e_month OUT VARCHAR2,
        e_month_sum OUT er_entry.pay_amount%TYPE)
    IS
    BEGIN
        SELECT TO_CHAR(entry_date, 'MM'), SUM(pay_amount)
        INTO e_month, e_month_sum
        FROM er_entry
        WHERE TO_CHAR(entry_date, 'YYYY') = e_year 
        GROUP BY TO_CHAR(entry_date, 'MM'); 
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No record.');
    END display_month_sum_pp;
END;





--Any changes in package specification need to run recompile--
ALTER PACKAGE easyrecord_pkg COMPILE;