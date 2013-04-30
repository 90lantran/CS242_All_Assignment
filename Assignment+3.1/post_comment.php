<?php
/**
 * Created by JetBrains PhpStorm.
 * User: shengchao
 * Date: 3/14/13
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */

include 'Connection.php';

$author = mysql_real_escape_string($_POST['name']);
$comment_body = mysql_real_escape_string($_POST['comment_body']);
$parent_id = mysql_real_escape_string($_POST['parent_id']);
$assignment_path = mysql_real_escape_string(($_POST['assignment_path']));

$filter = "SELECT replace_word FROM RedFlag WHERE red_word = '" . $comment_body . "'";
$result = mysql_query($filter);
if(mysql_num_rows($result)) {
	$row = mysql_fetch_assoc($result);
	$comment_body = $row['replace_word'];
}

$reDirect = "location: http://web.engr.illinois.edu/~huangfu2/ViewCode.php?file=" . $assignment_path;

$q = "INSERT INTO Comment (parent_id, writer_name, assignment_path, content)
        VALUES ('$parent_id', '$author', '$assignment_path', '$comment_body')";
$r = mysql_query($q);
if(mysql_affected_rows()==1) {
    header( $reDirect );
}
else {
    echo "Comment cannot be posted. Please try again.";
}
?>

