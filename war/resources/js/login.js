$(function() {
	var $orders = $('#orders');

	$.ajax({
		type : 'GET',
		url : '/AntSpringMVC/root',
		success : function(orders) {
			$.each(orders, function(i, order) {
				$orders.append('<li>name  ' + order.name + '</li>');
			});
		}
	});
});