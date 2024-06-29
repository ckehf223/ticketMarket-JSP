document.addEventListener('DOMContentLoaded',()=>{
	const noticeModifyBtn = document.getElementById('noticeModifyButton');
	noticeModifyBtn.addEventListener('click', () => {
		let query = {
			title: document.getElementById('adminNoticeTitle').value,
			content: document.getElementById('noticeContentText').value,
			num:document.getElementById('noticeNum').value
		}
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/ticketMarket/mg/noticeModifyPro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				alert('수정이 완료되었습니다.')
				window.location.href = '/ticketMarket/mg/noticeList.do';
			} else if (xhr.readyState === 4) {
				console.error('Error:', xhr.statusText);
			}
		}
		xhr.onerror = function() {
			console.error('Request failed');
		};
		xhr.send("title=" + encodeURIComponent(query.title)
		+"&content=" + encodeURIComponent(query.content)
		+"&num=" + encodeURIComponent(query.num));
	})
})