package org.anch.view;

import lombok.AllArgsConstructor;

import java.util.Scanner;
@AllArgsConstructor
public class ViewProvider {

    private final Scanner scanner;

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String readString() {
        return scanner.nextLine();
    }

    public byte readByte() {
        return Byte.parseByte(scanner.nextLine());
    }

    public int readShort() {
        return Integer.parseInt(scanner.nextLine());
    }

    public int readInt() {
        return Integer.parseInt(scanner.nextLine());
    }
    public double readDouble() {
        return Double.parseDouble(scanner.nextLine());
    }

    public boolean readBoolean() {
        return Boolean.parseBoolean(scanner.nextLine());
    }

}
