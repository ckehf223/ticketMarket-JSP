document.addEventListener('DOMContentLoaded', () => {
	const registerBtn = document.getElementById('registerButton');
	registerBtn.addEventListener('click', () => {
		let query = {
			title: document.getElementById('adminNoticeTitle').value,
			content: document.getElementById('noticeContentText').value
		}
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/ticketMarket/mg/noticeWritePro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				window.location.href = '/ticketMarket/mg/noticeList.do';
			} else if (xhr.readyState === 4) {
				console.error('Error:', xhr.statusText);
			}
		}
		xhr.onerror = function() {
			console.error('Request failed');
		};
		xhr.send("title=" + encodeURIComponent(query.title)
		+ "&content=" + encodeURIComponent(query.content));
	});
	
	const cencelBtn = document.getElementById('cencelButton');
	cencelBtn.addEventListener('click',()=>{
		window.location.href='/ticketMarket/mg/noticeList.do';
	})
});