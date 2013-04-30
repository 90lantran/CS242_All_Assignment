<?php
include 'Connection.php';

echo "Post Testing...";
$query = "SELECT * FROM Comment WHERE assignment_path = 'Assignment2.0/.project' AND parent_id = 0";
$result = mysql_query($query);
$row = mysql_fetch_assoc($result);
if($row['content'] == "hello"){
	echo "pass<br>";
}

echo "<br><br>";

echo "Reply Testing...";
$query = "SELECT * FROM Comment WHERE assignment_path = 'Assignment2.0/.project' AND parent_id <> 0";
$result = mysql_query($query);
$row = mysql_fetch_assoc($result);
if($row['content'] == "good"){
	echo "pass<br>";
}

echo "<br><br>";

echo "Filter Testing...";
$red_word = "shit";
$query = "SELECT * FROM RedFlag WHERE red_word = '" .$red_word. "'";
$result = mysql_query($query);
$row = mysql_fetch_assoc($result);
if($row['replace_word'] <> "shit"){
	echo "pass<br>";
}

echo "<br><br>";

echo "Escape Testing...";
$writer_name = "shen'chao";
$saft_name = mysql_real_escape_string($writer_name);
$saft_content = mysql_real_escape_string($content);
if($saft_name <> $writer_name){
	echo "pass<br>";
}
