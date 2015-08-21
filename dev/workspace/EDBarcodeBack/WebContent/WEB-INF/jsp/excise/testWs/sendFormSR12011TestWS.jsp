<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="fadeIn active">
	<h4>EDBarcodeBack SendFormSR12011</h4>
	<form action="/EDBarcodeBack/doControllerSendFormSR12011.htm" method="post">
		
		<table style="height: 500px;">
		
			<tr>
				<td><input style="width: 500px;" type="text" type="text" width="300px" value="http://localhost:8080/EDBarcodeBack/testWsSendFormSR12011.htm" /></td>
			</tr>
			
			<tr>
				<td><br></td>
			</tr>
			
			<tr>
				<td><input type="submit" /></td>
			</tr>

			<tr>
				<td><br></td>
			</tr>

			<tr>
				<td>Request</td>
				<td>Response</td>
			</tr>

			<tr>
				<td>
					<textarea rows="1" cols="1" style="height: 500px; width: 500px;" name="strInput"></textarea>
				</td>

				<td>
					<textarea rows="1" cols="1" style="height: 500px; width: 500px;" name="strXML">${strXML}</textarea>
				</td>
			</tr>

		</table>

	</form>
	<form action="/EDBarcodeBack/menuEBarcodeBackTestWS.htm" method="GET">
		<input type="submit" value="back" />
	</form>
</div>