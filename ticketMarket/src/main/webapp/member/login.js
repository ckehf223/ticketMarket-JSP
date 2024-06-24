document.addEventListener('DOMContentLoaded',()=>{
	let joinBtn = document.getElementById('join');
	joinBtn.addEventListener('click',()=>{window.location.href = '/ticketMarket/registerForm.do'});
	
	let loginBtn = document.getElementById('loginButton');
	loginBtn.addEventListener('click',()=>{
		let query = {
			id: document.getElementById('userId').value,
			pw: document.getElementById('userPw').value
		};
		let errorMg = document.getElementById('message');
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/ticketMarket/loginPro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				var data = xhr.responseText;
				var str1 = '<p id="ck">';
				var loc = data.indexOf(str1);
				var len = str1.length;
				var check = data.substr(loc + len, 1);
				if (check == '1') {
					window.location.href = '/ticketMarket/index.do';
				} else if (check == '0') {
					errorMg.innerHTML = '비밀번호를 틀렸습니다.'
					errorMg.style.color='red';
					document.getElementById('userPw').value = '';
				} else {
					errorMg.innerHTML = '아이디 또는 비밀번호가 맞지 않아요. 다시 입력해 주세요.';
					errorMg.style.color='red';
					document.getElementById('userId').value = '';
					document.getElementById('userPw').value = '';
				}
			} else if (xhr.readyState === 4) {
				console.error('Error:', xhr.statusText);
			}
		};
		xhr.onerror = function() {
			console.error('Request failed');
		};
		xhr.send("userId=" + encodeURIComponent(query.id) +
			"&userPw=" + encodeURIComponent(query.pw));
	});
});

	