document.addEventListener('DOMContentLoaded',()=>{
	let commentDeleteBtn = document.getElementById('commentAreaButton');
	commentDeleteBtn.addEventListener('click',()=>{
		let num = document.getElementById('comment_num').value;
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/ticketMarket/commentDelPro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.send("num=" + encodeURIComponent(num));
	})
});