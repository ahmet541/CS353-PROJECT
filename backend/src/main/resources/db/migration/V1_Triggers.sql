DROP TRIGGER IF EXISTS update_post_owner_trigger ON "User";
DROP TRIGGER IF EXISTS update_post_owner_trigger_regular_user ON Regular_User;
DROP TRIGGER IF EXISTS update_post_owner_trigger_company ON Company;

CREATE OR REPLACE FUNCTION update_post_owner_view()
    RETURNS TRIGGER AS $$
BEGIN
    -- Update the corresponding row in the view when the "User" table is updated
    IF TG_OP = 'UPDATE' AND TG_TABLE_NAME = 'User' THEN
        UPDATE post_owner_detail
        SET avatar = NEW.avatar
        WHERE userId = NEW.id;
    END IF;

    -- Update the corresponding row in the view when the "Regular_User" table is updated
    IF TG_OP = 'UPDATE' AND TG_TABLE_NAME = 'Regular_User' THEN
        UPDATE post_owner_detail
        SET fullName = CONCAT_WS(' ', NEW.first_name, NEW.last_name)
        WHERE userId = NEW.id;
    END IF;

    -- Update the corresponding row in the view when the "Company" table is updated
    IF TG_OP = 'UPDATE' AND TG_TABLE_NAME = 'Company' THEN
        UPDATE post_owner_detail
        SET fullName = NEW.companyName
        WHERE userId = NEW.id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_post_owner_trigger
    AFTER INSERT OR UPDATE ON "User"
    FOR EACH ROW
EXECUTE FUNCTION update_post_owner_view();

CREATE TRIGGER update_post_owner_trigger_regular_user
    AFTER UPDATE ON Regular_User
    FOR EACH ROW
EXECUTE FUNCTION update_post_owner_view();

CREATE TRIGGER update_post_owner_trigger_company
    AFTER UPDATE ON Company
    FOR EACH ROW
EXECUTE FUNCTION update_post_owner_view();
CREATE TRIGGER update_post_owner_trigger
    INSTEAD OF UPDATE ON post_owner_detail
    FOR EACH ROW
EXECUTE FUNCTION update_post_owner_view();


--The function below for updating Recruiter table after an update on Verifies table
--CREATE OR REPLACE FUNCTION update_recruiter()
--   RETURNS TRIGGER AS $$
--BEGIN
    --INSERT INTO recruiter ()
--END;