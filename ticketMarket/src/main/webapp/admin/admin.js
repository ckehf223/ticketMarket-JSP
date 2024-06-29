document.addEventListener('DOMContentLoaded', () => {
	const openUserBtn = document.getElementById('openUserButton');
	const dropdown_user = document.getElementById('dropdown_user');
	openUserBtn.addEventListener('click', () => {
		if (dropdown_user.style.display === 'none') {
			dropdown_user.style.display = 'flex';
		} else {
			dropdown_user.style.display = 'none';
		}
	})
	const openPerformaceBtn = document.getElementById('openPerformaceButton');
	const dropdown_pf = document.getElementById('dropdown_pf');
	openPerformaceBtn.addEventListener('click', () => {
		if (dropdown_pf.style.display === 'none') {
			dropdown_pf.style.display = 'flex';
		} else {
			dropdown_pf.style.display = 'none';
		}
	})
	const openNoticeBtn = document.getElementById('openNoticeButton');
	const dropdown_notice = document.getElementById('dropdown_notice');
	openNoticeBtn.addEventListener('click', () => {
		if (dropdown_notice.style.display === 'none') {
			dropdown_notice.style.display = 'flex';
		} else {
			dropdown_notice.style.display = 'none';
		}
	})

	
	const logoutBtn = document.getElementById('logoutButton');
	logoutBtn.addEventListener('click', () => {
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/ticketMarket/mg/logoutPro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				window.location.href = '/ticketMarket/mg/index.do';
			} else if (xhr.readyState === 4) {
				console.error('Error:', xhr.statusText);
			}
		}
		xhr.onerror = function() {
			console.error('Request failed');
		};
		xhr.send();
	})

});


function onDeleteUser(userNo) {
	let yesNo = prompt('정말 삭제하시겠습니까? YES/NO');
	if (yesNo.toUpperCase() === 'YES') {
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/ticketMarket/mg/userDeletePro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				alert('삭제가 완료되었습니다.');
				window.location.href = '/ticketMarket/mg/userDelete.do';
			} else if (xhr.readyState === 4) {
				console.error('Error:', xhr.statusText);
			}
		}
		xhr.onerror = function() {
			console.error('Request failed');
		};
		xhr.send("userNo=" + encodeURIComponent(userNo));
	}
	
}


function onUpdateProduct(pf_no){
	window.location.href=`/ticketMarket/mg/modifyProduct.do?pf_no=${pf_no}`;
}

function onDeleteProduct(pf_no){
	let yesNo = prompt('정말 삭제하시겠습니까? YES/NO');
	if (yesNo.toUpperCase() === 'YES') {
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/ticketMarket/mg/productDeletePro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				alert('공연 삭제가 완료되었습니다.');
				window.location.href = '/ticketMarket/mg/productList.do';
			} else if (xhr.readyState === 4) {
				console.error('Error:', xhr.statusText);
			}
		}
		xhr.onerror = function() {
			console.error('Request failed');
		};
		xhr.send("pf_no=" + encodeURIComponent(pf_no));
	}
}