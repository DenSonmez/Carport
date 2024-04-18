union()
{
    translate([0.0, 0.0, 290.25])
    {
        union()
        {
            union()
            {
                union()
                {
                    union()
                    {
                        union()
                        {
                            union()
                            {
                                union()
                                {
                                    union()
                                    {
                                        linear_extrude(height = 0.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
                                        {
                                            scale([0.0, 0.0])
                                            {
                                                M8();
                                            }
                                        }
                                        translate([0.0, 66.75, 0.0])
                                        {
                                            linear_extrude(height = 19.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                                            {
                                                scale([600.0, 4.5])
                                                {
                                                    M8();
                                                }
                                            }
                                        }
                                    }
                                    translate([0.0, 131.25, 0.0])
                                    {
                                        linear_extrude(height = 19.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                                        {
                                            scale([600.0, 4.5])
                                            {
                                                M8();
                                            }
                                        }
                                    }
                                }
                                translate([0.0, 195.75, 0.0])
                                {
                                    linear_extrude(height = 19.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                                    {
                                        scale([600.0, 4.5])
                                        {
                                            M8();
                                        }
                                    }
                                }
                            }
                            translate([0.0, 260.25, 0.0])
                            {
                                linear_extrude(height = 19.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                                {
                                    scale([600.0, 4.5])
                                    {
                                        M8();
                                    }
                                }
                            }
                        }
                        translate([0.0, 324.75, 0.0])
                        {
                            linear_extrude(height = 19.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                            {
                                scale([600.0, 4.5])
                                {
                                    M8();
                                }
                            }
                        }
                    }
                    translate([0.0, 389.25, 0.0])
                    {
                        linear_extrude(height = 19.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                        {
                            scale([600.0, 4.5])
                            {
                                M8();
                            }
                        }
                    }
                }
                translate([0.0, 453.75, 0.0])
                {
                    linear_extrude(height = 19.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                    {
                        scale([600.0, 4.5])
                        {
                            M8();
                        }
                    }
                }
            }
            translate([0.0, 518.25, 0.0])
            {
                linear_extrude(height = 19.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                {
                    scale([600.0, 4.5])
                    {
                        M8();
                    }
                }
            }
        }
    }
    translate([0.0, 300.0, 300.0])
    {
        linear_extrude(height = 3.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
        {
            scale([600.0, 600.0])
            {
                M8();
            }
        }
    }
    translate([0.0, 300.0, 293.75])
    {
        union()
        {
            translate([300.0, 0.0, 0.0])
            {
                linear_extrude(height = 12.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                {
                    scale([2.5, 600.0])
                    {
                        M8();
                    }
                }
            }
            translate([-300.0, 0.0, 0.0])
            {
                linear_extrude(height = 12.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                {
                    scale([2.5, 600.0])
                    {
                        M8();
                    }
                }
            }
        }
    }
    translate([0.0, 0.0, 293.75])
    {
        union()
        {
            translate([0.0, 600.0, 0.0])
            {
                linear_extrude(height = 12.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                {
                    scale([600.0, 2.5])
                    {
                        M8();
                    }
                }
            }
            translate([0.0, 0.0, 0.0])
            {
                linear_extrude(height = 12.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                {
                    scale([600.0, 2.5])
                    {
                        M8();
                    }
                }
            }
        }
    }
    translate([0.0, 300.0, 273.25])
    {
        union()
        {
            translate([285.0, 0.0, 0.0])
            {
                linear_extrude(height = 14.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                {
                    scale([4.5, 600.0])
                    {
                        M8();
                    }
                }
            }
            translate([-285.0, 0.0, 0.0])
            {
                linear_extrude(height = 14.5, twist = 0.0, scale = 1.0, slices = 1, center = true)
                {
                    scale([4.5, 600.0])
                    {
                        M8();
                    }
                }
            }
        }
    }
}

module M8()
{
    polygon
    (
        points =
        [
            [-0.5, -0.5], 
            [0.5, -0.5], 
            [0.5, 0.5], 
            [-0.5, 0.5]
        ],
        paths =
        [
            [0, 1, 2, 3]
        ]
    );
}
