/*
 * Copyright (C) 2015 Uhlig e Korovsky Tecnologia Ltda - ME
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.uktech.bmt.bacula.lib;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class DataPackage {
    private String data;
    private Integer signal;

    public DataPackage() {
    }

    public DataPackage(String data) {
        this.data = data;
    }

    public DataPackage(Integer signal) {
        this.signal = signal;
    }
    
    public DataPackage(String data, Integer signal) {
        this.data = data;
        this.signal = signal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getSignal() {
        return signal;
    }

    public void setSignal(Integer signal) {
        this.signal = signal;
    }
    
    public Integer getReturnCode() {
        if (data == null && data.length() < 4) {
            return 0;
        }
        int rc = 0;
        try {
                rc = Integer.parseInt(data.substring(0, 4));
        } catch (NumberFormatException ne) {}
        return rc;
    }
    
    public String getMessage() {
        if (data != null && data.length() > 5) {
            return data.substring(5);
        }
        return null;
    }
}
