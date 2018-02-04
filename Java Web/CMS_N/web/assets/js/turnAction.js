//该函数操控首页菜单的点击事件
//出发struts的action
function turnAction(it) {

	url = "";
	id = "";

	switch (arguments[0]) {
	case "login":
		url = "login!login.action";
		break;
	// case "exit":
	// 	url = "logout!logout.action";
	// 	break;
		
		
	case "searchReader":
		account = document.getElementById("account").value;
		url = "search_reader!searchByAccount.action?readerAccount=" + account;
		break;
	case "deleteReader":
		id = document.getElementById("id").innerHTML;
		if (confirm("你确定删除该用户信息吗？")) {
			url = "delete_reader!delete.action?id=" + id;
		} else {
			alert("取消删除信息");
		}
		break;
	case "updateReader":
		updateText = document.getElementById("updateText").value;
		id = document.getElementById("id").innerHTML;
		if (confirm("你确定修改用户信息吗？")) {
			url = "update_reader!update.action?id=" + id + "&updateType="
					+ arguments[1] + "&updateText=" + updateText;
		} else {
			alert("取消修改信息");
		}
		break;

		
	case "searchBook":
		number = document.getElementById("number").value;
		url = "search_book!searchByNumber.action?bookNumber=" + number;
		break;
	case "deleteBook":
		id = document.getElementById("id").innerHTML;
		if (confirm("你确定删除该书籍信息吗？")) {
			url = "delete_book!delete.action?id=" + id;
		} else {
			alert("取消删除信息");
		}
		break;
	case "updateBook":
		updateText = document.getElementById("updateText").value;
		id = document.getElementById("id").innerHTML;
		if (confirm("你确定修改书籍信息吗？")) {
			url = "update_book!update.action?id=" + id + "&updateType="
					+ arguments[1] + "&updateText=" + updateText;
		} else {
			alert("取消修改信息");
		}
		break;

		
	case "searchStore":
		name = document.getElementById("name").value;
		url = "search_store!searchByName.action?storeName=" + name;
		break;
	case "deleteStore":
		id = document.getElementById("id").innerHTML;
		if (confirm("你确定删除该书店信息吗？")) {
			url = "delete_store!delete.action?id=" + id;
		} else {
			alert("取消删除信息");
		}
		break;
	case "updateStore":
		updateText = document.getElementById("updateText").value;
		id = document.getElementById("id").innerHTML;
		if (confirm("你确定修改书店信息吗？")) {
			url = "update_store!update.action?id=" + id + "&updateType="
					+ arguments[1] + "&updateText=" + updateText;
		} else {
			alert("取消修改信息");
		}
		break;

		
	default:
		alert(arguments[0] + "未完成");
		break;
	}

	window.location.href = url;
}

// function addCookie(name,value,days,path){ /**添加设置cookie**/
// var name = escape(name);
// var value = escape(value);
// var expires = new Date();
// expires.setTime(expires.getTime() + days * 3600000 * 24);
// //path=/，表示cookie能在整个网站下使用，path=/temp，表示cookie只能在temp目录下使用
// path = path == "" ? "" : ";path=" + path;
// //GMT(Greenwich Mean Time)是格林尼治平时，现在的标准时间，协调世界时是UTC
// //参数days只能是数字型
// var _expires = (typeof days) == "string" ? "" : ";expires=" +
// expires.toUTCString();
// document.cookie = name + "=" + value + _expires + path;
// }

