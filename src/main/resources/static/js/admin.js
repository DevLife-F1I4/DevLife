function search_user(){
	location.href = "/adminpage/"+document.querySelector('input[name="user"]').value;
}

function changeGrade(){
	const grade = document.querySelector('#user-grade').value;
	const id = document.querySelector('#userid').innerText;
	fetch('/api/admin/user/'+id, {
		method: 'PATCH',
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify({
			grade: grade,
		})
	}).then(response=>{
		if (response.ok){
			alert('완료');
			location.reload();
		}
	}).catch(error => console.error(error.message));
}

function withdraw(flag){
	const id = document.querySelector('#userid').innerText;
	console.log(id);
	const url = "/api/admin/user/"+ id +"/" + (flag ? "true" : "false");
	console.log(url);
	fetch(url, {
		method: 'DELETE',
	}).then(response=>{
		if (response.ok){
			alert('완료');
			location.reload();
		}
	}).catch(error => console.error(error.message));
}
