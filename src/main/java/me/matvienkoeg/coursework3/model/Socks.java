package me.matvienkoeg.coursework3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Socks {
    private Color color;
    private Size size;
    private int cottonPart;
}
