/**
 * 
 */

const loginFormView = () => {
	$('#register-form').fadeOut(300);
	setTimeout(function() {
		document.getElementById('login-form').querySelectorAll('input').forEach(input => input.value = '');
		$('#login-form').fadeIn(300);
	}, 300);
}

const registerFormView = () => {
	$('#login-form').fadeOut(300);
	setTimeout(function() {
		document.getElementById('register-form').querySelectorAll('input').forEach(input => input.value = '');
		$('#register-form').fadeIn(300);
	}, 300);
}

$(window).on('load', function() {
	setTimeout(function() {
		$('body').addClass('appear');
	}, 10);
});

const logout = (e) => {
	e.preventDefault();
	Swal.fire({
		title: 'ログアウト',
		text: "本当にログアウトしますか?",
		icon: 'question',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#c0c0c0',
		confirmButtonText: 'Logout'
	}).then((result) => {
		if (result.isConfirmed) {
			window.location.href = e.target.href;
		}
	});
}

$(window).on('load',function(){
  $("#splash").delay(1500).fadeOut('slow');
  $("#splash_logo").delay(1200).fadeOut('slow');
  $('body').addClass('appear');
});
