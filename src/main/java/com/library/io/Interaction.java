package com.library.io;

import com.library.entity.Book;
import com.library.entity.Student;
import com.library.mapper.LibraryMapper;
import com.library.utils.Sql;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;

@Log
public class Interaction {

    public static void initLog() {
        LogManager manager = LogManager.getLogManager();
        try {
            manager.readConfiguration(Resources.getResourceAsStream("log.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void banner() {
        System.out.println("==============");
        System.out.println("1. 录入学生信息");
        System.out.println("2. 录入书籍信息");
        System.out.print("输入你想进行的操作(输入0退出): ");
    }

    public static int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            return -1;
        }
    }

    public static boolean process(int choice) {
        if (choice == 0) {
            System.out.println("退出系统");
            return false;
        } else if (choice == 1) {
            if (!addStudent())
                System.out.println("录入学生信息失败！");
            else
                System.out.println("录入学生信息成功！");
        } else if (choice == 2) {
            if (!addBook())
                System.out.println("录入书籍信息失败！");
            else
                System.out.println("录入书籍信息成功！");
        } else {
            System.out.println("输入错误，请重新输入");
        }
        return true;
    }

    public static boolean addStudent() {
        System.out.println("请输入学生姓名:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("请输入学生性别:");
        String sex = scanner.nextLine();
        System.out.println("请输入学生年级:");

        int grade;
        if (scanner.hasNextInt()) {
            grade = scanner.nextInt();
        } else {
            System.out.println("错误:输入的年级必须是整数！");
            return false;
        }
        if (grade <= 0) {
            System.out.println("错误:输入的年级必须是正整数！");
            return false;
        }

        LibraryMapper libraryMapper = Sql.getMapper(true);
        Student student = new Student(name, sex, grade);
        if (libraryMapper.addStudent(student) > 0) {
            log.info("New Student:" + student);
            return true;
        }
        return false;
    }

    public static boolean addBook() {
        System.out.println("请输入图书名称:");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        System.out.println("请输入图书简介:");
        String description = scanner.nextLine();
        System.out.println("请输入图书价格:");

        double price;
        if (scanner.hasNextDouble()) {
            price = scanner.nextDouble();
        } else {
            System.out.println("错误:输入的价格必须是小数！");
            return false;
        }
        if (price <= 0.0) {
            System.out.println("错误:输入的价格必须是正数！");
            return false;
        }

        LibraryMapper libraryMapper = Sql.getMapper(true);
        Book book = new Book(title, description, price);
        if (libraryMapper.addBook(book) > 0) {
            log.info("New Book:" + book);
            return true;
        }
        return false;
    }
}
