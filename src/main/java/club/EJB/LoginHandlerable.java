package club.EJB;

import club.DAO.user.User;

public interface LoginHandlerable {

	void onLogin(User tryLoginUser);

}
