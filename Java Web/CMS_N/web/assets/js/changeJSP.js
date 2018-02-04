//该函数操控首页菜单的点击事件
//转到该目录下jsp页面
function changeJSP(it) {

	var wrapper = document.getElementById("wrapper");
	var t = wrapper.getElementsByClassName("col-md-12");
	var g;
	for (var i = 0; i < t.length; i++) {
		if (t[i].id == "need") {
			g = t[i];
		}
	}	
	
	var h_list = g.getElementsByTagName("*");
	for (var j = 0; j < h_list.length; j++) {
		if (h_list[j].id == "frame_jsp") {
			// iframe的S标签
			var h = h_list[j];
		}
	}
	

	switch(arguments[1]) {
		case 'showAllC':
			h.src = '../iframe/all-classroom.jsp'
			break;
		case 'userInfo':
			h.src = '../iframe/user-info.jsp'
			break;
		case 'showOccupy':
			h.src = '../iframe/occupy.jsp'
			break;
	// case 'showReader' :
	// 	h.src = "operation-jsp/select-all-reader.jsp";
	// 	break;
	// case 'searchReader' :
	// 	h.src = "operation-jsp/update-reader.jsp";
	// 	break;
	// case 'addReader' :
	// 	h.src = "operation-jsp/add-reader.jsp";
	// 	break;
	// case 'updateReader' :
	// 	h.src = "operation-jsp/update-reader.jsp";
	// 	break;
	//
	// case 'showBook' :
	// 	h.src = "operation-jsp/select-all-book.jsp";
	// 	break;
	// case 'searchBook' :
	// 	h.src = "operation-jsp/update-book.jsp";
	// 	break;
	// case 'addBook' :
	// 	h.src = "operation-jsp/add-book.jsp";
	// 	break;
	// case 'updateBook' :
	// 	h.src = "operation-jsp/update-book.jsp";
	// 	break;
	//
	// case 'showStore' :
	// 	h.src = "operation-jsp/select-all-store.jsp";
	// 	break;
	// case 'searchStore' :
	// 	h.src = "operation-jsp/update-store.jsp";
	// 	break;
	// case 'addStore' :
	// 	h.src = "operation-jsp/add-store.jsp";
	// 	break;
	// case 'updateStore' :
	// 	h.src = "operation-jsp/update-store.jsp";
	// 	break;
	//
	// case 'showAPPInfo' :
	// 	h.src = "operation-jsp/showAPPInfo.jsp";
	// 	break;
		
	default:
		alert(arguments[1] + " 未完成?");
	}
	

}