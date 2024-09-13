package Pages;

import Base.PageBase;

public class ModalsPage extends PageBase {

	public ModalsPage goToSimpleModal() {
		click("btnSimpleModal_ID");
		return this;
	}

	public ModalsPage goToFormModal() {
		click("btnFormModal_ID");
		return this;
	}

	public ModalsPage closeSimpleModal() {
		click("btnCloseSimpleModal_CSS");
		return this;
	}

	public ModalsPage modalSendMessage(String name, String email, String message) {
		type("txtName_ID", name);
		type("txtEmail_ID", email);
		type("txtMessage_ID", message);

		return this;
	}

	public ModalsPage submit() {
		click("btnSubmitFormModal_CSS");
		return this;
	}
}
