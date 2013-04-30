<?php
include 'Parser.php';




/**
 * This file test some functionality for the File Entry Class
 *
 * @author Joshua Harris
 */

class myTest extends PHPUnit_Framework_TestCase {

    public function testDirName() {
        $dirObject = new DirectoryClass("/test", "1234", "huangfu2", "08-03-2013");
        $subDir = new DirectoryClass("/test/sub", "1234", "huangfu2", "08-03-2013");
        $dirObject -> addSubDir("mySub", $subDir);
        $subArray = $dirObject -> getSubDirArray();
        $dirName = null;
        foreach ($subArray as $name => $object){
            $dirName = $name;
            break;
        }
        assert($dirName == "mySub");
    }

    public function testAuthor() {
        $dirObject = new DirectoryClass("/test", "1234", "huangfu2", "08-03-2013");
        $subDir = new DirectoryClass("/test/sub", "1234", "huangfu2", "08-03-2013");
        $dirObject -> addSubDir("mySub", $subDir);
        $subArray = $dirObject -> getSubDirArray();
        $dirAuthor = null;
        foreach ($subArray as $name => $object){
            $dirAuthor = $object->getDirAuthor();
            break;
        }
        assert($dirAuthor == "huangfu2");
    }

    public function testParseDir() {
        $parser = parseList();
        $dirList = $parser["dirArray"];
        $dirAuthor = null;
        foreach ($dirList as $name => $object) {
            $dirAuthor = $object->getDirAuthor();
            assert($dirAuthor == "huangfu2");
        }
    }

    public function testParseLog() {
        $logList = parseLog();
        $logAuthor = null;
        foreach ($logList as $revision => $object) {
            $logAuthor = $object->getLogAuthor();
            break;
        }
        assert($logAuthor == "huangfu2");
    }

}

?>
